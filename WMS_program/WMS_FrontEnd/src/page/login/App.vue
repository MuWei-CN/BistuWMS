<script setup>
import { onMounted } from 'vue'
import { useRoute,useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

onMounted(()=>{
  router.push('/login')
})

</script>

<template>
  <div id="Dynpanel">
    <RouterView></RouterView>
  </div>
</template>

<style scoped>

</style>