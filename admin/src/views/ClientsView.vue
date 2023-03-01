<script setup>
import {
  mdiMonitorCellphone,
  mdiTableBorder,
  mdiTableOff,
  mdiGithub,
  mdiHome,
  mdiChartTimelineVariant,
  mdiAccountMultiple,
  mdiCartOutline,
  mdiReload,
  mdiChartPie, mdiPlus, mdiAccount,
}
  from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import NotificationBar from "@/components/NotificationBar.vue";
import TableSampleClients from "@/components/TableProperty.vue";
import CardBox from "@/components/CardBox.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import BaseButton from "@/components/BaseButton.vue";
import CardBoxComponentEmpty from "@/components/CardBoxComponentEmpty.vue";
import TableClient from "@/components/TableClient.vue";
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiAccount" title="Clients" main>
        <router-link to="/add-client">
          <BaseButton
            target="_blank"
            :icon="mdiPlus"
            label="Add new client"
            color="contrast"
            rounded-full
            small
          />
        </router-link>
      </SectionTitleLineWithButton>
      <!--   we can call tha notification here
      <NotificationBar color="info" :icon="mdiMonitorCellphone">
        <b>Responsive table.</b> Collapses on mobile
      </NotificationBar>
          <NotificationBar color="danger" :icon="mdiTableOff">
        <b>Empty table.</b> When there's nothing to show
      </NotificationBar>
      -->
      <CardBox class="mb-6" has-table>
        <TableClient/>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
