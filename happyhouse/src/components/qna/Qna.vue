<template>
  <b-container>
    <div class="mb-5">
      <h3 class="text-center">Q&A</h3>
    </div>
    <router-view :userId="this.user.userId" />
  </b-container>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['user']),
  },
};
</script>
