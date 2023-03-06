<script setup>
import { computed, ref } from "vue";
import { useMainStore } from "@/stores/main";
import { mdiEye, mdiTrashCan,mdiHumanEdit} from "@mdi/js";
import CardBoxModal from "@/components/CardBoxModal.vue";
import TableCheckboxCell from "@/components/TableCheckboxCell.vue";
import BaseLevel from "@/components/BaseLevel.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import BaseButton from "@/components/BaseButton.vue";
import UserAvatar from "@/components/UserAvatar.vue";
import AdminService from "@/services/AdminService";


defineProps({
  checkable: Boolean,
});

const mainStore = useMainStore();


const items = computed(() => AdminService.getAdmins().toString());

const isModalActive = ref(false);

const isModalDangerActive = ref(false);

const perPage = ref(4);

const currentPage = ref(0);

const checkedRows = ref([]);

const itemsPaginated = computed(() =>
  items.value.slice(
    perPage.value * currentPage.value,
    perPage.value * (currentPage.value + 1)
  )
);

const numPages = computed(() => Math.ceil(items.value.length / perPage.value));

const currentPageHuman = computed(() => currentPage.value + 1);

const pagesList = computed(() => {
  const pagesList = [];

  for (let i = 0; i < numPages.value; i++) {
    pagesList.push(i);
  }

  return pagesList;
});

const remove = (arr, cb) => {
  const newArr = [];

  arr.forEach((item) => {
    if (!cb(item)) {
      newArr.push(item);
    }
  });

  return newArr;
};

const checked = (isChecked, admin) => {
  if (isChecked) {
    checkedRows.value.push(admin);
  } else {
    checkedRows.value = remove(
      checkedRows.value,
      (row) => row.id === admin.id
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
      <th v-if="checkable" />
      <th>Id</th>
      <th>Name</th>
      <th>Email</th>
      <th>Password</th>

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
      <td data-label="Password">
        {{ admin.password }}
      </td>




      <td class="before:hidden lg:w-1 whitespace-nowrap">
        <BaseButtons type="justify-start lg:justify-end" no-wrap>

          <BaseButton
            color="danger"
            :icon="mdiTrashCan"
            small
            @click="isModalDangerActive = true"
          />

<!--          <router-link to="/update_admin">-->
          <BaseButton
            color="success"
            :icon="mdiHumanEdit"
            to="/update-admin"
            small
          />
<!--          </router-link>-->
        </BaseButtons>
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
  name: "AdminView",
  data(){
    return{
      admins:[],
      ADMIN_API_BASE_URL : "http://localhost:8080/manage_admin/admins",
    };

  },
  methods : {

    async getAdmins() {
    await axios.get(this.ADMIN_API_BASE_URL)
      .then(response=>this.admins=response.data)
      .catch(error=>console.log(error))
  }
  },
    mounted(){

    this.getAdmins();
  }

};
</script>
