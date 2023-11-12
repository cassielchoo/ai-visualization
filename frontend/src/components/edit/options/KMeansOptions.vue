<template>
    <v-text-field type="number" label="簇个数 Num of Dusters" variant="underlined" v-model="num"></v-text-field>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';


const num:Ref<number>=ref(0)
</script>

<style scoped>

</style>