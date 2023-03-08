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
            <FormControl :options="selectOptions" v-model="property.category"/>
          </FormField>
          <FormField label="Type">
            <FormControl :options="selectOptions" v-model="property.type"/>
          </FormField>
        </FormField>

        <FormField>
          <FormField label="Bathroom Count">
            <FormControl type="number" v-model="property.bathroomCount"/>
          </FormField>
          <FormField label="Room Count ">
            <FormControl type="number" v-model="property.roomCount"/>
          </FormField>
        </FormField>
        <FormField>
          <FormField label="Area">
            <FormControl type="text" v-model="property.area"/>
          </FormField>
          <FormField label="Price">
            <FormControl :options="selectOptions" v-model="property.price"/>
          </FormField>
        </FormField>
        <FormField>
          <FormField label="Location">
            <FormControl :options="selectOptions" v-model="property.location"/>
          </FormField>
          <div id="map"></div>

          <FormField label="Equiped">

            <FormCheckRadioGroup
              name="sample-switch"
              type="switch"
              :options="{ one: ' '}"
              v-model="property.equiped"
            />
          </FormField>
        </FormField>

        <FormField label="Description" class="w-full">
          <FormControl
            type="textarea"
            placeholder="Put your description here"
            v-model="property.description"
          />
        </FormField>
        <FormFilePicker label="Upload"/>
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
        category: "",
        type: "",
        bathroomCount: "",
        roomCount: "",
        area: "",
        price: "",
        location: "",
        equiped: false,
        description: "",
        image: "",
      },
    };
  },
  methods: {
    async addNewProperty() {
      await axios.post(this.RESERVATION_API_BASE_URL + "/add-property", this.property)
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
