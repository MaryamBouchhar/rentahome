<script setup>
import {computed, ref, onMounted} from "vue";
import {useMainStore} from "@/stores/main";
import {
  mdiAccountMultiple,
  mdiChartTimelineVariant,
  mdiReload,
  mdiHome,
  mdiChartPie,
} from "@mdi/js";
import * as chartConfig from "@/components/Charts/chart.config.js";
import LineChart from "@/components/Charts/LineChart.vue";
import SectionMain from "@/components/SectionMain.vue";
import CardBoxWidget from "@/components/CardBoxWidget.vue";
import CardBox from "@/components/CardBox.vue";
import TableSampleClients from "@/components/TableProperty.vue";
import BaseButton from "@/components/BaseButton.vue";
import CardBoxTransaction from "@/components/CardBoxTransaction.vue";
import CardBoxClient from "@/components/CardBoxClient.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import axios from "axios";

const chartData = ref(null);

const fillChartData = () => {
  chartData.value = chartConfig.sampleChartData();
};

onMounted(() => {
  fillChartData();
});

const mainStore = useMainStore();

const clientBarItems = computed(() => mainStore.clients.slice(0, 4));

const transactionBarItems = computed(() => mainStore.history);

//dashboard metrics
const clients = ref(0);
const properties = ref(0);
const todayReservations = ref(0);
const newestClients = ref([]);

//get metrics
const getTotalClients = async () => {
  const response = await axios.get("http://localhost:8080/dashboard/clients-count");
  clients.value = response.data;
  console.log("Clients: ", clients.value);
};

const getTotalProperties = async () => {
  const response = await axios.get("http://localhost:8080/dashboard/properties-count");
  properties.value = response.data;
  console.log("Properties: ", properties.value);
};

const getTodayReservations = async () => {
  const response = await axios.get("http://localhost:8080/dashboard/today-reservations");
  todayReservations.value = response.data;
  console.log("Reservations: ", todayReservations.value);
};

const getNewestClients = async () => {
  const response = await axios.get("http://localhost:8080/dashboard/newest-clients");
  newestClients.value = response.data;
  console.log("Newest Clients: ", newestClients.value);
};

onMounted(() => {
  getTotalClients();
  getTotalProperties();
  getTodayReservations();
  getNewestClients();
});
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>

      <SectionTitleLineWithButton
        :icon="mdiChartTimelineVariant"
        title="Overview"
        main
      >
      </SectionTitleLineWithButton>

      <div class="grid grid-cols-1 gap-6 lg:grid-cols-3 mb-6">
        <CardBoxWidget
          trend="12%"
          trend-type="up"
          color="text-emerald-500"
          :icon="mdiAccountMultiple"
          :number="clients"
          label="Clients"
        />
        <CardBoxWidget
          trend="35%"
          trend-type="up"
          color="text-blue-500"
          :icon="mdiHome"
          :number="properties"
          label="Properties"
        />
        <CardBoxWidget
          trend="8%"
          trend-type="alert"
          color="text-red-500"
          :icon="mdiChartTimelineVariant"
          :number="todayReservations"
          label="Today Reservations"
        />
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-6">
        <div class="flex flex-col justify-between">
          <CardBoxTransaction
            v-for="(transaction, index) in transactionBarItems"
            :key="index"
            :amount="transaction.amount"
            :date="transaction.date"
            :business="transaction.business"
            :type="transaction.type"
            :name="transaction.name"
            :account="transaction.account"
          />
        </div>
        <div class="flex flex-col justify-between">
          <CardBoxClient
            v-for="client in clientBarItems"
            :key="client.id"
            :name="client.name"
            :login="client.login"
            :date="client.created"
            :progress="client.progress"
          />
        </div>
      </div>

      <SectionTitleLineWithButton :icon="mdiAccountMultiple" title="Clients"/>
      <CardBox has-table>
        <TableSampleClients/>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
