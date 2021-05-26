<template>
  <div>
    <div class="mb-5">
      <h1 class="text-center">공지사항</h1>
    </div>
    <router-view :userId="this.user.userId" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['user']),
  },
};
</script>
