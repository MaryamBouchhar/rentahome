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
  mdiChartPie,  mdiPlus,
  mdiClipboardList,

} from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import NotificationBar from "@/components/NotificationBar.vue";

import CardBox from "@/components/CardBox.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import BaseButton from "@/components/BaseButton.vue";
import CardBoxComponentEmpty from "@/components/CardBoxComponentEmpty.vue";
import TableSampleReservation from "@/components/TableSampleReservation.vue";
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon=" mdiClipboardList" title="Booking Requests" main>

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
        <TableSampleReservation/>
      </CardBox>


    </SectionMain>
  </LayoutAuthenticated>
</template>
