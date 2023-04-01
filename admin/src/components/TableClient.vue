<script setup>
import {computed, ref} from "vue";
import {useMainStore} from "@/stores/main";
import {mdiEye, mdiTrashCan, mdiImageEdit} from "@mdi/js";
import CardBoxModal from "@/components/CardBoxModal.vue";
import TableCheckboxCell from "@/components/TableCheckboxCell.vue";
import BaseLevel from "@/components/BaseLevel.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import BaseButton from "@/components/BaseButton.vue";
import UserAvatar from "@/components/UserAvatar.vue";
import ClientService from "@/services/ClientService";

defineProps({
  checkable: Boolean,
});

const mainStore = useMainStore();
const isModalActive = ref(false);
const isModalDangerActive = ref(false);
const checkedRows = ref([]);
const remove = (arr, cb) => {
  const newArr = [];

  arr.forEach((item) => {
    if (!cb(item)) {
      newArr.push(item);
    }
  });

  return newArr;
};

const checked = (isChecked, client) => {
  if (isChecked) {
    checkedRows.value.push(client);
  } else {
    checkedRows.value = remove(
      checkedRows.value,
      (row) => row.id === client.id
    );
  }
};
</script>

<template>
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
    <p>Lorem ipsum dolor sit amet <b>adipiscing elit</b></p>
    <p>This is sample modal</p>
  </CardBoxModal>

  <div v-if="checkedRows.length" class="p-3 bg-gray-100/50 dark:bg-slate-800">
    <span
      v-for="checkedRow in checkedRows"
      :key="checkedRow.id"
      class="inline-block px-2 py-1 rounded-sm mr-2 text-sm bg-gray-100 dark:bg-slate-700"
    >
      {{ checkedRow.name }}
    </span>
  </div>

  <table>
    <thead>
    <tr>
      <th v-if="checkable"/>
      <th>Id</th>
      <th>Name</th>
      <th>Email</th>
      <th>Phone number</th>


    </tr>
    </thead>
    <tbody>
    <tr v-for="client in paginatedClients" :key="client.id">
      <TableCheckboxCell
        v-if="checkable"
        @checked="checked($event, client)"
      />

      <td data-label="Id">
        {{ client.id }}
      </td>

      <td data-label="Name">
        {{ client.name }}
      </td>
      <td data-label="Email">
        {{ client.email }}
      </td>
      <td data-label="Phone">
        {{ client.phone }}
      </td>

    </tr>
    </tbody>
  </table>
  <div class="p-3 lg:px-6 border-t border-gray-100 dark:border-slate-800">
    <BaseLevel>
      <BaseButtons>
        <BaseButton
          v-for="page in pagesList"
          :key="page"
          :active="page === currentPage"
          :label="page + 1"
          :color="page === currentPage ? 'lightDark' : 'whiteDark'"
          small
          @click="currentPage = page"
        />
      </BaseButtons>
      <small>Page {{ currentPageHuman }} of {{ numPages }}</small>
    </BaseLevel>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: "ClientView",
  data() {
    return {
      clients: [],
      currentPage: 0,
      pageSize: 5,
      CLIENT_API_BASE_URL: "http://localhost:8080/api/clients/clients",
    };

  },
  methods: {
    async getClients() {
      await axios.get(this.CLIENT_API_BASE_URL)
        .then(response => this.clients = response.data)
        .catch(error => console.log(error))
    }
  },
  computed: {
    paginatedClients: function () {
      const startIndex = this.currentPage * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.clients.slice(startIndex, endIndex);
    },
    numPages: function () {
      return Math.ceil(this.clients.length / this.pageSize);
    },
    currentPageHuman: function () {
      return this.currentPage + 1;
    },
    pagesList: function () {
      return Array.from({length: this.numPages}, (v, k) => k);
    }
  },
  mounted() {
    this.getClients();
  }
};
</script>
