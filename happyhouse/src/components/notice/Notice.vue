<template>
  <div>
    <div>
      <h3 class="text-center">공지사항</h3>
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
