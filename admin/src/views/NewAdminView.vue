<script setup>
import {reactive, ref} from "vue";
import {mdiBallotOutline, mdiAccount, mdiMail, mdiGithub, mdiPlus, mdiLock} from "@mdi/js";
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
import swal from 'sweetalert';

window.Swal = swal;
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
        title="New Admin"
        main
      >
        <router-link to="/admins">
          <BaseButton

            target="_blank"
            :icon="mdiLock"
            label="Show Admins"
            color="contrast"
            rounded-full
            small
          />
        </router-link>
      </SectionTitleLineWithButton>
      <div class="container w-5/12 mx-auto">
        <CardBox form @submit.prevent="submit">

          <FormField label="Name">
            <FormControl type="email" v-model="name" required/>
          </FormField>
          <FormField label="Email">
            <FormControl type="email" v-model="email" required/>
          </FormField>
          <FormField label="Password" >
            <FormControl v-model="password" required/>
          </FormField>

          <BaseDivider/>


          <template #footer>
            <BaseButtons class="ml-14 mr-0">
              <BaseButton class="ml-20 " type="submit" color="warning" label="Add" @click="addAdmin"/>
              <BaseButton class="ml-2" type="reset" color="warning" outline label="Reset"/>
            </BaseButtons>
          </template>
        </CardBox>
      </div>
    </SectionMain>


  </LayoutAuthenticated>
</template>

<script>

 import axios from "axios";

 export default {
 data(){
return {
name : "",
email: "",
password : "",
}
},
props : [],
methods : {

  async addAdmin() {
    const newAdmin = {
      name: this.name,
      email: this.email,
      password: this.password,
    };


    axios.post('http://localhost:8080/manage-admin/add-admin', newAdmin
    )
      .then(() => {
        swal({
          text: "Admin Added Successfully!",
          icon: "success",
          closeOnClickOutside: false,
        });
      })
      .catch(error => {
        console.log(error);
      });

  }
}

}
</script>
