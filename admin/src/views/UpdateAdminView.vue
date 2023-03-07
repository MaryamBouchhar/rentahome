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
        title="Update Admin"
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

          <FormField label="Name" >
            <FormControl type="email" v-model="data.name" required/>
          </FormField>
          <FormField label="Email">
            <FormControl  type="email" v-model="data.email" required/>
          </FormField>
          <FormField label="Password" >
            <FormControl v-model="data.password" required/>
          </FormField>

          <BaseDivider/>


          <template #footer>
            <BaseButtons class="ml-14 mr-0">
              <BaseButton   class="ml-20 " type="submit" color="warning" label="Update Admin" @click="updateAdmin"/>
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

     data: {
       id: null,
       name: '',
       email: '',
       password:'',
     },
  }
  },
   props : [],
   mounted() {

  const id = this.$route.params.id
     axios.get(`http://localhost:8080/manage-admin/admins/${id}`).then((response) => {
       this.data = response.data
     })
   },
   methods : {

  async updateAdmin() {
    const updatedAdmin = {
      name: this.data.name,
      email: this.data.email,
      password: this.data.password,
    };


    axios.put(`http://localhost:8080/manage-admin/update/${this.data.id}`, updatedAdmin
    )
      .then(() => {
        swal({
          text: "Admin Updated Successfully!",
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
