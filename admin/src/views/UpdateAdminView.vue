<script setup>
import { mdiPlus, mdiLock} from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import CardBox from "@/components/CardBox.vue";
import FormField from "@/components/FormField.vue";
import FormControl from "@/components/FormControl.vue";
import BaseDivider from "@/components/BaseDivider.vue";
import BaseButton from "@/components/BaseButton.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";

import swal from 'sweetalert';
window.Swal = swal;

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
            <FormControl type="text" v-model="data.name"  autocomplete required/>
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

   methods : {

    getAdminById(){
      const id = this.$route.params.id;
      axios.get(`http://localhost:8080/manage-admin/admins/${id}`).then((response) => {
        this.data = response.data
      })
    },

    updateAdmin() {
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
        });;
      })
      .catch(error => {
        console.log(error);
      });

    }

   },
   mounted() {

     this.getAdminById()

   },

 }
</script>
