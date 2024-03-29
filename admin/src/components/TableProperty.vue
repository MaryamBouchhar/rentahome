<script setup>
import {computed, ref} from "vue";
import {useMainStore} from "@/stores/main";
import {mdiEye, mdiPencil, mdiTrashCan, mdiUpdate} from "@mdi/js";
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

    <span> <b>Categpry :</b></span><span>{{ selectedProperty.category }}</span>
    <br>
    <span> <b>Bathroom Count :</b></span><span>{{ selectedProperty.bathroom_count }}</span>
    <br>
    <span> <b>Room Count :</b></span><span>{{ selectedProperty.room_count }}</span>
    <br>
    <span> <b>Area :</b></span><span>{{ selectedProperty.area }}</span>
    <br>
    <span> <b>Rent Type:</b></span><span>{{ selectedProperty.rent_type }}</span>
    <br>
    <span> <b>Price :</b></span><span>{{ selectedProperty.price }}</span>
    <br>
    <span> <b>Location :</b></span><span>{{ selectedProperty.location }}</span>
    <br>
    <span> <b>Publish Date :</b></span><span>{{ selectedProperty.category }}</span>
    <br>
    <span> <b>Equipped :</b></span><span v-if="selectedProperty._equipped">Equipped</span>
    <span v-else>Not Equipped</span>
    <br>
    <span> <b>Status :</b></span><span>{{ selectedProperty.status }}</span>
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
      <th>Date</th>
      <th/>
    </tr>
    </thead>
    <tbody>
    <tr v-for="property in paginatedProperties" :key="property.id">
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
        {{ property.location.city }}
      </td>
      <td data-label="Equipped" class="lg:w-42">
        <div v-if="property.equipped" class="badge badge-outline badge-success">Equipped</div>
        <div v-else class="badge badge-outline badge-error">Not equipped</div>
      </td>
      <td data-label="Price">
        {{ property.price }}
      </td>
      <td data-label="Status">
        <div v-if="property.status == 'Available'" class="badge badge-success">{{ property.status }}</div>
        <div v-else class="badge badge-error">{{ property.status }}</div>
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
            @click="isModalActive=true,getProperty(property)"
          />
          <BaseButton
            color="warning"
            :icon="mdiPencil"
            :to="'/update-property/' + property.id"
            small
          />
          <BaseButton
            color="danger"
            :icon="mdiTrashCan"
            small
            @click="deleteProperty(property.id)"
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
import swal from "sweetalert";

export default {
  name: "PropertyView",
  data() {
    return {
      properties: [],
      selectedProperty: {},
      isModalActive: false,
      currentPage: 0,
      pageSize: 5,
      PROPERTY_API_BASE_URL: `http://localhost:8080/manage-properties/properties`,
    };
  },
  methods: {
    async getProperties() {
      await axios.get(this.PROPERTY_API_BASE_URL)
        .then(response => {
          this.properties = response.data
          // this.properties.forEach(property => {
          //   property.publish_date = new Date(property.publish_date).toLocaleDateString();
          // })
          console.log(this.properties)
        })
        .catch(error => console.log(error))
    },
    async getProperty(property) {

      await axios.get(this.PROPERTY_API_BASE_URL + `/${property.id}`)
        .then(response => {
          this.selectedProperty = response.data
        })
        .catch(error => console.log(error))
      console.log(this.selectedProperty.location.city)
    },
    deleteProperty(id) {
      swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this property!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
        .then((willDelete) => {
          if (willDelete) {
            axios.delete(`http://localhost:8080/manage-properties/delete/${id}`)
              .then(response => {
                swal("Poof! Your property has been deleted!", {
                  icon: "success",
                });
                this.getProperties()
              })
              .catch(error => console.log(error))
          } else {
            swal("Your property is safe!");
          }
        });
    },
  },
  computed: {
    paginatedProperties: function () {
      const startIndex = this.currentPage * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.properties.slice(startIndex, endIndex);
    },
    numPages: function () {
      return Math.ceil(this.properties.length / this.pageSize);
    },
    currentPageHuman: function () {
      return this.currentPage + 1;
    },
    pagesList: function () {
      return Array.from({length: this.numPages}, (v, k) => k);
    }
  },
  mounted() {
    this.getProperties();
  }
};
</script>
