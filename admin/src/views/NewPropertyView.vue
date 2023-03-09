<script setup>
import {reactive, ref} from "vue";
import {mdiPlus} from "@mdi/js";
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


</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton
        :icon="mdiPlus"
        title="New Property"
        main
      >
        <router-link to="/properties">
          <BaseButton
            target="_blank"
            :icon="mdiProperty"
            label="Show properties"
            color="contrast"
            rounded-full
            small
          />
        </router-link>
      </SectionTitleLineWithButton>
      <CardBox form @submit.prevent="submit">
        <FormField>
          <FormField label="Category">
            <FormControl type="text" :options="selectOptions" v-model="property.category"/>
          </FormField>
          <FormField label="Rent Type">
            <FormControl type="text" :options="selectOptions" v-model="property.rent_type"/>
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
            <FormControl type="number" :options="selectOptions" v-model="property.price"/>
          </FormField>
        </FormField>
        <FormField>
          <!--          <FormField label="Location">-->
          <!--            <FormControl :options="selectOptions" v-model="property.location"/>-->
          <!--          </FormField>-->
          <FormField label="City">
            <FormControl type="text" :options="selectOptions" v-model="property.location.city"/>
          </FormField>
          <FormField label="Address">
            <FormControl type="text" :options="selectOptions" v-model="property.location.address"/>
          </FormField>
          <FormField>
            <FormField label="Longitute">
              <FormControl type="number" :options="selectOptions" v-model="property.location.longitude"/>
            </FormField>
            <FormField label="Latitude">
              <FormControl type="number" :options="selectOptions" v-model="property.location.latitude"/>
            </FormField>
          </FormField>
          <div id="map"></div>

          <FormField label="Equiped">
            <FormCheckRadioGroup
              name="sample-switch"
              type="switch"
              :options="{ one: ' '}"
              v-model="property._equipped"/>
          </FormField>
        </FormField>

        <FormField label="Description" class="w-full">
          <FormControl
            type="textarea"
            placeholder="Put your description here"
            v-model="property.description"
          />
        </FormField>
        <FormField label="Images">
          <input type="file"
                 id="images"
                 name="images"
                 class="file-input file-input-bordered file-input-info w-full max-w-xs"
                 @change="onImageSelected"
                 multiple/>
        </FormField>
        <BaseDivider/>

        <template #footer>

          <BaseButtons class=" flex items-center justify-center">
            <BaseButton type="submit" color="warning" label="Add" @click="addNewProperty"/>
            <BaseButton type="reset" color="warning" outline label="Reset"/>
          </BaseButtons>

        </template>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>

<script>
import axios from "axios";

export default {
  name: "NewPropertyView",
  data() {
    return {
      RESERVATION_API_BASE_URL: "http://localhost:8080/manage-properties",
      categories: [
        {value: "one", label: "One"},
        {value: "two", label: "Two"},
        {value: "three", label: "Three"},
      ],
      types: [
        {value: "one", label: "One"},
        {value: "two", label: "Two"},
        {value: "three", label: "Three"},
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
        _equipped: false,
        publish_date: "2021-05-01",
      },
      images: [],
    };
  },
  methods: {
    async addNewProperty() {
      const formData = new FormData();
      formData.append("property", JSON.stringify(this.property));
      for (let i = 0; i < this.images.length; i++) {
        formData.append("images", this.images[i]);
      }

      const headers = {
        'Content-Type': 'multipart/form-data'
      }
      //send property and images to backend
      await axios.post(this.RESERVATION_API_BASE_URL + "/add-property", formData, headers)
        .then(() => {
          swal({
            text: "Property Added Successfully!",
            icon: "success",
            closeOnClickOutside: false,
          });
        })
        .catch(error => {
          console.log(error);
        });
      console.log(this.property);
    },
    async getCategories() {
      await axios.get(this.RESERVATION_API_BASE_URL + "/categories")
        .then(response => {
          this.categories = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    async getRentTypes() {
      await axios.get(this.RESERVATION_API_BASE_URL + "/rent-types")
        .then(response => {
          this.types = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    //change the status of is equiped
    onSwitchChange() {
      this.property._equipped = !this.property._equipped;
      console.log("switchListener", this.property._equipped);
    },
    //multiple file upload
    onImageSelected(e) {
      for (let i = 0; i < e.target.files.length; i++) {
        this.images.push(e.target.files[i]);
      }
      console.log(this.property.images)
    },
    async latestPropertyId() {
      const current_property_id = 0;
      await axios.get(this.RESERVATION_API_BASE_URL + "/latest-property-id")
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
    const map = new google.maps.Map(document.getElementById("map"), {
      center: {lat: 37.7749, lng: -122.4194},
      zoom: 12,
    });
  }
};
</script>

<style>
#map {
  width: 100%;
  height: 100%;
}
</style>
