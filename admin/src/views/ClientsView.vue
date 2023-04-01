<script setup>
import {
 mdiAccount,
}
  from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import CardBox from "@/components/CardBox.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import TableClient from "@/components/TableClient.vue";
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiAccount" title="Clients" main>

      </SectionTitleLineWithButton>
      <CardBox class="mb-6" has-table>
        <TableClient/>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
