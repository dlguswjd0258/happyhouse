<template>
  <div>
    <div>
      <h3 class="text-center">Q&A</h3>
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
