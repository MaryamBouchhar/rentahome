<script setup>
import {
  mdiHome,
  mdiPlus,
}
  from "@mdi/js";
import SectionMain from "@/components/SectionMain.vue";
import TableProperty from "@/components/TableProperty.vue";
import CardBox from "@/components/CardBox.vue";
import LayoutAuthenticated from "@/layouts/LayoutAuthenticated.vue";
import SectionTitleLineWithButton from "@/components/SectionTitleLineWithButton.vue";
import BaseButton from "@/components/BaseButton.vue";
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiHome" title="Properties" main>
        <router-link to="/add-property">
          <BaseButton
            target="_blank"
            :icon="mdiPlus"
            label="Add new property"
            color="contrast"
            rounded-full
            small
          />
        </router-link>
      </SectionTitleLineWithButton>
      <CardBox class="mb-6" has-table>
        <TableProperty/>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
