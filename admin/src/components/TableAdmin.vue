<script setup>
import {computed, ref} from "vue";
import {useMainStore} from "@/stores/main";
import {mdiEye, mdiTrashCan, mdiHumanEdit} from "@mdi/js";
import CardBoxModal from "@/components/CardBoxModal.vue";
import TableCheckboxCell from "@/components/TableCheckboxCell.vue";
import BaseLevel from "@/components/BaseLevel.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import BaseButton from "@/components/BaseButton.vue";
import UserAvatar from "@/components/UserAvatar.vue";
import AdminService from "@/services/AdminService";
</script>

<template class="container w-5/12 mx-auto">

  <CardBoxModal v-model="isModalActive" title="Sample modal">
    <p>Lorem ipsum dolor sit amet <b>adipiscing elit</b></p>
    <p>This is sample modal</p>
  </CardBoxModal>

  <CardBoxModal
    v-model="isModalDangerActive"
    title="Please confirm"
    button="danger"
    has-cancel
  >
  </CardBoxModal>


  <table >
    <thead >
    <tr>
      <th v-if="checkable"/>
      <th>Id</th>
      <th>Name</th>
      <th>Email</th>


      <th/>
    </tr>
    </thead>
    <tbody>
    <tr v-for="admin in admins" :key="admin.id">
      <TableCheckboxCell
        v-if="checkable"
        @checked="checked($event, admin)"
      />

      <td data-label="Id">
        {{ admin.id }}
      </td>
      <td data-label="Name">
        {{ admin.name }}
      </td>
      <td data-label="Email">
        {{ admin.email }}
      </td>



      <td class="before:hidden lg:w-1 whitespace-nowrap">
        <BaseButtons type="justify-start lg:justify-end" no-wrap>



          <BaseButton
            color="success"
            :icon="mdiHumanEdit"
            :to = "'/update-admin/' + admin.id"
            small
          />
        </BaseButtons>
      </td>
    </tr>
    </tbody>
  </table>
  <div>
    <button @click="prevPage">Prev</button>
    <span>Page {{ currentPage + 1 }} of {{ totalPages }}</span>
    <button @click="nextPage">Next</button>
  </div>

</template>
<script>
import axios from 'axios';
export default {
  name: "AdminView",
  data() {
    return {
      admins: [],
      currentPage: 0,
      pageSize: 10,
      totalPages: 0,
      ADMIN_API_BASE_URL: "http://localhost:8080/manage-admin/admins",
    };

  },
  methods: {

    async getAdmins() {
      await axios.get(`http://localhost:8080/manage-admin/admins?page=${this.currentPage}&size=${this.pageSize}`)
        .then(response => this.admins = response.data)
      this.totalPages = response.headers['x-total-pages']
        .catch(error => console.log(error))
    }
  },
  mounted() {
    this.getAdmins();
  },
  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++
      this.getClients()
    }
  },
  prevPage() {
    if (this.currentPage > 0) {
      this.currentPage--
      this.getClients()
    }
  }

};
</script>
