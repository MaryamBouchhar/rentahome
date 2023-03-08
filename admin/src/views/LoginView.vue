<script setup>
import { mdiAccount, mdiAsterisk } from "@mdi/js";
import SectionFullScreen from "@/components/SectionFullScreen.vue";
import CardBox from "@/components/CardBox.vue";
import FormField from "@/components/FormField.vue";
import FormControl from "@/components/FormControl.vue";
import BaseButton from "@/components/BaseButton.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import LayoutGuest from "@/layouts/LayoutGuest.vue";




</script>

<template>
  <LayoutGuest>
    <SectionFullScreen v-slot="{ cardClass }" >
      <CardBox :class="cardClass" form @submit.prevent="submit">
        <FormField label="Email" help="Please enter your email">
          <FormControl
            v-model="email"
            :icon="mdiAccount"
            name="login"
            autocomplete="email"
          />
        </FormField>

        <FormField label="Password" help="Please enter your password">
          <FormControl
            v-model="password"
            :icon="mdiAsterisk"
            type="password"
            name="password"
            autocomplete="current-password"
          />
        </FormField>
        <template #footer>
          <BaseButtons>
            <BaseButton type="submit" color="info" label="Login"  @click="login"/>

          </BaseButtons>
        </template>
      </CardBox>
    </SectionFullScreen>
  </LayoutGuest>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      email:'',
      password:''


    }
  },
  methods:{

    async login(){

      const login = {
        email: this.email,
        password: this.password
      };

      const response = axios.post('http://localhost:8080/login/auth',login
      )
        .then(response => {
          console.log(response.data);
          if (response.data.success){
            this.$router.push({name: 'dashboard'})
          }
        })
        .catch(error => {
          console.log(error);
        });
    }


  }


}

</script>
