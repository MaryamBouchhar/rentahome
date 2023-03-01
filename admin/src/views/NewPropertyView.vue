<script setup>
import {reactive, ref} from "vue";
import {mdiBallotOutline, mdiAccount, mdiMail, mdiGithub, mdiPlus} from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import CardBox from "@/components/CardBox.vue";
import FormCheckRadioGroup from "@/components/FormCheckRadioGroup.vue";
import FormFilePicker from "@/components/FormFilePicker.vue";
import FormField from "@/components/FormField.vue";
import FormControl from "@/components/FormControl.vue";
import BaseDivider from "@/components/BaseDivider.vue";
import BaseButton from "@/components/BaseButton.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import SectionTitle from "@/components/SectionTitle.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import NotificationBarInCard from "@/components/NotificationBarInCard.vue";

const selectOptions = [
  {id: 1, label: "Business development"},
  {id: 2, label: "Marketing"},
  {id: 3, label: "Sales"},
];

const form = reactive({
  name: "John Doe",
  email: "john.doe@example.com",
  phone: "",
  department: selectOptions[0],
  subject: "",
  question: "",
});

const customElementsForm = reactive({
  checkbox: ["lorem"],
  radio: "one",
  switch: ["one"],
  file: null,
});

const submit = () => {
  //
};

const formStatusWithHeader = ref(true);

const formStatusCurrent = ref(0);

const formStatusOptions = ["info", "success", "danger", "warning"];

const formStatusSubmit = () => {
  formStatusCurrent.value = formStatusOptions[formStatusCurrent.value + 1]
    ? formStatusCurrent.value + 1
    : 0;
};
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
        <FormField label="Bathroom Count " >
          <FormControl v-model="form.phone" type="number"
          />
        </FormField>
        <FormField label="Room Count " >
          <FormControl
            v-model="form.phone"
            type="number"
          />
        </FormField>
</FormField>
        <FormField>
        <FormField label="Stage Count " >
          <FormControl v-model="form.phone" type="number"
          />
        </FormField>
          <FormField label="Surface" >
            <FormControl v-model="form.phone" type="text"
            />
          </FormField>
        </FormField>
        <FormField>
          <FormField label="Location" >
            <FormControl v-model="form.department" :options="selectOptions"/>
          </FormField>
          <FormField label="Equiped" >
<br>
              <FormCheckRadioGroup
                v-model="customElementsForm.radio"
                name="sample-radio"
                type="radio"
                :options="{ one: 'Yes', two: 'No' }"
              />
            </FormField>
          </FormField>

        <FormFilePicker v-model="customElementsForm.file" label="Upload"/>
        <BaseDivider/>



        <template #footer>
          <BaseButtons>
            <BaseButton type="submit" color="info" label="Submit"/>
            <BaseButton type="reset" color="info" outline label="Reset"/>
          </BaseButtons>
        </template>
      </CardBox>
    </SectionMain>
















  </LayoutAuthenticated>
</template>
