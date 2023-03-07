<script setup>
import {computed, ref} from "vue";
import {useMainStore} from "@/stores/main";
import {mdiEye, mdiImageEdit, mdiTrashCan, mdiUpdate} from "@mdi/js";
import CardBoxModal from "@/components/CardBoxModal.vue";
import TableCheckboxCell from "@/components/TableCheckboxCell.vue";
import BaseLevel from "@/components/BaseLevel.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import BaseButton from "@/components/BaseButton.vue";
import UserAvatar from "@/components/UserAvatar.vue";
import TableClient from "@/components/TableClient.vue";
import CardBox from "@/components/CardBox.vue";
import PropertyService from "@/services/PropertyService";

defineProps({
  checkable: Boolean,
});

const mainStore = useMainStore();

const items = computed(() => PropertyService.getProperties().toString());

const isModalActive = ref(false);

const isModalDangerActive = ref(false);

const perPage = ref(5);

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

const checked = (isChecked, property) => {
  if (isChecked) {
    checkedRows.value.push(property);
  } else {
    checkedRows.value = remove(
      checkedRows.value,
      (row) => row.id === property.id
    );
  }
};
</script>

<template>

  <CardBoxModal v-model="isModalActive" title="View Detail Property">

    <span> <b>Category :</b></span>
    <span class="flex justify-center flex">Villa</span>

    <br>
    <span> <b>Type :</b></span><span>Villa</span>
    <br>
    <span> <b>Bathroom Count :</b></span><span>Villa</span>
    <br>
    <span> <b>Room Count :</b></span><span>Villa</span>
    <br>
    <span> <b>Area :</b></span><span>Villa</span>
    <br>
    <span> <b>Price :</b></span><span>Villa</span>
    <br>
    <span> <b>Location :</b></span><span>Villa</span>
    <br>
    <span> <b>Publish Date :</b></span><span>Villa</span>
    <br>
    <span> <b>Equipped :</b></span><span>Villa</span>
    <br>
    <span> <b>Status :</b></span><span>Villa</span>
    <br>
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
      <th>Category</th>
      <th>Type</th>
      <th>City</th>
      <th>Equiped</th>
      <th>Price</th>
      <th>status</th>
      <th>Added at</th>
      <th/>
    </tr>
    </thead>
    <tbody>
    <tr v-for="property in properties" :key="property.id">
      <TableCheckboxCell
        v-if="checkable"
        @checked="checked($event, property)"
      />

      <td data-label="Id">
        {{ property.id }}
      </td>

      <td data-label="Category">
        {{ property.category }}
      </td>
      <td data-label="Type">
        {{ property.rent_type }}
      </td>
      <td data-label="City">
        {{ property.location }}
      </td>
      <td data-label="Equipped" class="lg:w-32">
       <progress
          class="flex w-2/5 self-center lg:w-full"
          max="100"
          :value="property.is_equipped"
        >

          {{ property.is_equipped }}
       </progress>
      </td>
      <td data-label="Price">
        {{ property.price }}
      </td>
      <td data-label="Status">
        {{ property.status }}
      </td>
      <td data-label="Created" class="lg:w-1 whitespace-nowrap">
        <small
          class="text-gray-500 dark:text-slate-400"
          :title="property.publish_date"
        >{{ property.publish_date }}</small
        >
      </td>

      <td class="before:hidden lg:w-1 whitespace-nowrap">
        <BaseButtons type="justify-start lg:justify-end" no-wrap>
          <BaseButton
            color="info"
            :icon="mdiEye"
            small
            @click="isModalActive = true"
          />
          <BaseButton
            color="danger"
            :icon="mdiTrashCan"
            small
            @click="isModalDangerActive = true"
          />
          <BaseButton
            color="success"
            :icon="mdiImageEdit"
            small
            @click="isModalActive = true"
          />
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
  name: "PropertyView",
  data() {
    return {
      properties: [],
      PROPERTY_API_BASE_URL: "http://localhost:8080/manage-properties/properties",
    };

  },
  methods: {

    async getProperties() {
      await axios.get(this.PROPERTY_API_BASE_URL)
        .then(response => this.properties = response.data)
        .catch(error => console.log(error))
    }
  },
  mounted() {
    this.getProperties();
  }

};
</script>
