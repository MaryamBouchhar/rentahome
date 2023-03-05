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
  mdiChartPie, mdiPlus, mdiLock,
}
  from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";

import TableAdmin from "@/components/TableAdmin.vue";
import CardBox from "@/components/CardBox.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import BaseButton from "@/components/BaseButton.vue";
import CardBoxComponentEmpty from "@/components/CardBoxComponentEmpty.vue";
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiLock" title="Admins" main>
        <router-link to="/add-admin">
          <BaseButton
            target="_blank"
            :icon="mdiPlus"
            label="Add new admin"
            color="contrast"
            rounded-full
            small
          />
        </router-link>
      </SectionTitleLineWithButton>

      <CardBox class="mb-6" has-table>
        <TableAdmin/>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
