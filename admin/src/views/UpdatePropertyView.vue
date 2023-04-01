<script setup>
import {reactive, ref} from "vue";
import {mdiHome, mdiUpdate} from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import CardBox from "@/components/CardBox.vue";
import FormCheckRadioGroup from "@/components/FormCheckRadioGroup.vue";
import FormFilePicker from "@/components/FormFilePicker.vue";
import FormField from "@/components/FormField.vue";
import FormControl from "@/components/FormControl.vue";
import BaseDivider from "@/components/BaseDivider.vue";
import BaseButton from "@/components/BaseButton.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import FormCheckRadio from "@/components/FormCheckRadio.vue";
import updatePropertyView from "@/views/UpdatePropertyView.vue";


</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton
        :icon="mdiUpdate"
        title="Update Property"
        main
      >
        <router-link to="/properties">
          <BaseButton
            target="_blank"
            :icon="mdiHome"
            label="Show properties"
            color="contrast"
            rounded-full
            small
          />
        </router-link>
      </SectionTitleLineWithButton>
      <CardBox form>
        <FormField>
          <FormField label="Category">

            <FormControl type="text" :options="categories" v-model="property.category"/>
          </FormField>
          <FormField label="Rent Type">
            <FormControl type="text" :options="types" v-model="property.rent_type"/>
          </FormField>
        </FormField>

        <FormField>
          <FormField label="Bathroom Count">
            <FormControl type="number" v-model="property.bathroom_count"/>
          </FormField>
          <FormField label="Room Count ">
            <FormControl type="number" v-model="property.room_count"/>
          </FormField>
        </FormField>
        <FormField>
          <FormField label="Area">
            <FormControl type="number" v-model="property.area"/>
          </FormField>
          <FormField label="Price">
            <FormControl type="number" v-model="property.price"/>
          </FormField>
        </FormField>
        <FormField>
          <FormField label="Location">
            <div ref="mapContainer" style="height: 344px;"></div>
          </FormField>
          <FormField label="City">
            <FormControl type="text" v-model="property.location.city"/>
          </FormField>
          <FormField label="Address">
            <FormControl type="text" v-model="property.location.address"/>
          </FormField>
          <div id="map"></div>

          <FormField label="Equiped">
            <FormControl type="text" :options="equipped" v-model="property.equipped"/>
          </FormField>
        </FormField>

        <FormField label="Description" class="w-full">
          <FormControl
            type="textarea"
            placeholder="Put your description here"
            v-model="property.description"
          />
        </FormField>
        <BaseDivider/>

        <template #footer>
          <BaseButtons class=" flex items-center justify-center">
            <BaseButton type="submit" color="warning" label="Edit Property" @click="updateProperty"/>
          </BaseButtons>
        </template>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>

<script>
import axios from "axios";
import {Map, View} from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import VectorSource from 'ol/source/Vector';
import VectorLayer from 'ol/layer/Vector';
import {transform} from 'ol/proj';

export default {
  name: "NewPropertyView",
  data() {
    return {
      Properties_API_BASE_URL: "http://localhost:8080/manage-properties",
      equipped: [
        {value: "true", label: "Yes"},
        {value: "false", label: "No"},
      ],
      categories: [
        {value: "House", label: "House"},
        {value: "Apartment", label: "Apartment"},
        {value: "Condo", label: "Condo"},
        {value: "Townhouse", label: "Townhouse"},
        {value: "Villa", label: "Villa"},
        {value: "Studio", label: "Studio"},
        {value: "Other", label: "Other"},
      ],
      types: [
        {value: "Daily", label: "Daily"},
        {value: "Monthly", label: "Monthly"},
      ],
      property: {
        description: "",
        category: "House",
        price: 0,
        area: 0,
        status: "Available",
        location: {
          address: "",
          city: "",
          longitude: 0,
          latitude: 0,
        },
        rent_type: "Daily",
        bathroom_count: 0,
        room_count: 0,
        equipped: false,
        publish_date: null,
      },
      images: [],
    };
  },
  methods: {

    getPropertyById() {
      const id = this.$route.params.id;
      axios.get('http://localhost:8080/manage-properties/properties/' + id)
        .then((response) => {
          this.property = response.data
          console.log("Property: " + this.property);
          console.log(this.property.category);
        })
    },

    async updateProperty() {
      this.property.category = this.property.category.value;
      this.property.rent_type = this.property.rent_type.value;
      this.property.equipped = this.property.equipped.value;

      const formData = new FormData();
      formData.append("property", JSON.stringify(this.property));

      //send property and images to backend
      await axios.put(`http://localhost:8080/manage-properties/update/${this.property.id}`, this.property)
        .then(() => {
          swal({
            text: "Property updated Successfully!",
            icon: "success",
            closeOnClickOutside: false,
          });
          // this.$router.go();
        })
        .catch(error => {
          console.log(error);
        });
      console.log(this.property);
    },
    async getCategories() {
      await axios.get(this.Properties_API_BASE_URL + "/categories")
        .then(response => {
          this.categories = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    async getRentTypes() {
      await axios.get(this.Properties_API_BASE_URLL + "/rent-types")
        .then(response => {
          this.types = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    //change the status of is equiped
    onSwitchChange(e) {
      this.property._equipped = e.target.checked;
      console.log("IS EQUIPED: " + this.property._equipped);
    },
    //multiple file upload
    onImageSelected(e) {
      for (let i = 0; i < e.target.files.length; i++) {
        this.images.push(e.target.files[i]);
      }
      console.log(this.property.images)
    },
    async latestPropertyId() {

      let current_property_id = 0;

      await axios.get(this.Properties_API_BASE_URL + "/latest-property-id")
        .then(response => {
          current_property_id = response.data;
        })
        .catch(error => {
          console.log(error);
        });

      return current_property_id;
    }
  },
  mounted() {

    this.getPropertyById();

    // Define the map view
    const view = new View({
      center: [0, 0],
      zoom: 2,
      projection: 'EPSG:3857'
    });


    // Define the map layer
    const layer = new TileLayer({
      source: new OSM(),
    });

    // Create the marker feature
    const marker = new Feature({
      geometry: new Point([0, 0]),
    });

    // Create the vector layer for the marker
    const vectorLayer = new VectorLayer({
      source: new VectorSource({
        features: [marker],
      }),
    });

    // Create the map object and set it as the view's target
    const map = new Map({
      target: this.$refs.mapContainer,
      layers: [layer, vectorLayer],
      view: view,
    });

    // Add an event listener to the map to move the marker to the clicked location
    map.on('click', (event) => {
      const [longitude, latitude] = transform(event.coordinate, 'EPSG:3857', 'EPSG:4326');
      //fill the location object
      this.property.location.longitude = longitude;
      this.property.location.latitude = latitude;
      console.log(`Latitude: ${latitude}, Longitude: ${longitude}`);
      console.log("location", this.property.location);
      marker.setGeometry(new Point(event.coordinate));
    });
  },

};
</script>

<style>
#map {
  width: 100%;
  height: 100%;
}
</style>
