<script setup>
import {reactive, ref} from "vue";
import { mdiPlus} from "@mdi/js";
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
            <FormControl v-model="form.department" :options="selectOptions"/>
          </FormField>
          <FormField label="Type">
            <FormControl v-model="form.department" :options="selectOptions"/>
          </FormField>
        </FormField>

        <FormField>
          <FormField label="Bathroom Count ">
            <FormControl v-model="form.phone" type="number"
            />
          </FormField>
          <FormField label="Room Count ">
            <FormControl
              v-model="form.phone"
              type="number"
            />
          </FormField>
        </FormField>
        <FormField>

          <FormField label="Area">
            <FormControl v-model="form.phone" type="text"
            />
          </FormField>
        </FormField>
        <FormField>
          <FormField label="Location">
            <FormControl v-model="form.department" :options="selectOptions"/>
          </FormField>
          <FormField label="Equiped">

            <FormCheckRadioGroup
              v-model="customElementsForm.switch"
              name="sample-switch"
              type="switch"

              :options="{ one: ' '}"
            />
          </FormField>
        </FormField>

        <FormField label="Description" class="w-full">
          <FormControl
            type="textarea"
            placeholder="Put your description here"
          />
        </FormField>
        <FormFilePicker v-model="customElementsForm.file" label="Upload"/>
        <BaseDivider/>


        <template #footer>

          <BaseButtons class=" flex items-center justify-center">
            <BaseButton type="submit" color="warning" label="Add"/>
            <BaseButton type="reset" color="warning" outline label="Reset"/>
          </BaseButtons>

        </template>
      </CardBox>
    </SectionMain>


  </LayoutAuthenticated>
</template>
