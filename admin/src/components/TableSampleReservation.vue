<script setup>
import {computed, ref} from "vue";
import {useMainStore} from "@/stores/main";
import {mdiClose, mdiCheck, mdiEye, mdiTrashCan} from "@mdi/js";
import CardBoxModal from "@/components/CardBoxModal.vue";
import TableCheckboxCell from "@/components/TableCheckboxCell.vue";
import BaseLevel from "@/components/BaseLevel.vue";
import BaseButtons from "@/components/BaseButtons.vue";
import BaseButton from "@/components/BaseButton.vue";
import UserAvatar from "@/components/UserAvatar.vue";


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

      <th>Property ID</th>
      <th>Client</th>
      <th>Status</th>
      <th>Added at</th>
      <th/>
    </tr>
    </thead>
    <tbody>
    <tr v-for="reservation in paginatedReservations" :key="reservation.id">
      <TableCheckboxCell
        v-if="checkable"
        @checked="checked($event, reservation)"
      />


      <td data-label="Name">
        {{ reservation.id_property }}
      </td>
      <td data-label="Company">
        {{ reservation.client.name }}
      </td>
      <td>
        <div class="badge badge-success" v-if="reservation.status=='Approved'">{{ reservation.status }}</div>
        <div class="badge badge-warning" v-else-if="reservation.status=='Pending'">{{ reservation.status }}</div>
        <div class="badge badge-error" v-else>{{ reservation.status }}</div>
      </td>
      <td data-label="Created" class="lg:w-1 whitespace-nowrap">
        <small
          class="text-gray-500 dark:text-slate-400"
          :title="reservation.created"
        >{{ reservation.created_at }}</small
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
            color="success"
            :icon="mdiCheck"
            small
            v-if="reservation.status=='Pending'"
            @click="approveReservation(reservation.id)"
          />
          <BaseButton
            color="danger"
            :icon="mdiClose"
            small
            v-if="reservation.status=='Pending'"
            @click="cancelReservation(reservation.id)"
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
import swal from 'sweetalert';

window.Swal = swal;

export default {
  name: "ReservationView",
  data() {
    return {
      reservations: [],
      currentPage: 0,
      pageSize: 5,
      RESERVATION_API_BASE_URL: "http://localhost:8080/manage-reservation",
    };

  },
  methods: {
    async getReservations() {
      await axios.get(this.RESERVATION_API_BASE_URL + "/reservations")
        .then(response => {
          this.reservations = response.data
          this.reservations.forEach(reservation => {
            reservation.created_at = new Date(reservation.created_at).toLocaleString()
          })
        })
        .catch(error => console.log(error))
      console.log(this.reservations)
    },
    async approveReservation(id) {
      Swal({
        title: "Are you sure?",
        text: "Once approved, you will not be able to revert this!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
        .then((willDelete) => {
          if (willDelete) {
            const reservation = this.getReservationById(id);
            reservation.status = "Approved";


            axios.put(this.RESERVATION_API_BASE_URL + "/update/" + id, reservation)
              .then(response => {
                console.log(response.data)
                Swal("Reservation has been approved!", {
                  icon: "success",
                });
                this.getReservations()
              })
              .catch(error => {
                console.log(error)
                Swal({
                  title: "Error!",
                  text: "Reservation is not approved!",
                  icon: "error",
                  button: "OK",
                });
              })
          } else {
            Swal("Reservation is not approved!");
          }
        });
    },
    async cancelReservation(id) {
      Swal({
        title: "Are you sure?",
        text: "Once approved, you will not be able to revert this!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
        .then((willDelete) => {
          if (willDelete) {
            const reservation = this.getReservationById(id);
            const start_date = new Date(reservation.start_date)
            const end_date = new Date(reservation.end_date)

            const formatted_sd = start_date.toLocaleString('en-US', {
              timeZone: 'UTC',
              year: 'numeric',
              month: '2-digit',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit',
              second: '2-digit',
            })

            const formatted_ed = end_date.toLocaleString('en-US', {
              timeZone: 'UTC',
              year: 'numeric',
              month: '2-digit',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit',
              second: '2-digit',
            })

            reservation.start_date = formatted_sd
            reservation.end_date = formatted_ed

            reservation.status = "Rejected";

            axios.put(this.RESERVATION_API_BASE_URL + "/update/" + id, reservation)
              .then(response => {
                console.log(response.data)
                Swal("Reservation has been rejected!", {
                  icon: "success",
                });
                //delete reservation
                this.deleteReservation(id)
                this.getReservations()
              })
              .catch(error => {
                console.log(error)
                Swal({
                  title: "Error!",
                  text: "Reservation is not rejected!",
                  icon: "error",
                  button: "OK",
                });
              })
          } else {
            Swal("Reservation is not rejected");
          }
        });
    },
    async deleteReservation(id) {
      await axios.delete(this.RESERVATION_API_BASE_URL + "/delete/" + id)
        .then(response => {
          console.log(response.data)
          this.getReservations()
        })
        .catch(error => {
          console.log(error)
        })
    },
    getReservationById(id) {
      return this.reservations.filter(reservation => reservation.id === id)[0];
    },
  },
  computed: {
    paginatedReservations: function () {
      const startIndex = this.currentPage * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.reservations.slice(startIndex, endIndex);
    },
    numPages: function () {
      return Math.ceil(this.reservations.length / this.pageSize);
    },
    currentPageHuman: function () {
      return this.currentPage + 1;
    },
    pagesList: function () {
      return Array.from({length: this.numPages}, (v, k) => k);
    }
  },
  mounted() {
    this.getReservations();
  }
};
</script>
