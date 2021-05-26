<template>
  <div>
    <div v-if="user.userId">
      <b-navbar toggleable="lg" type="dark" variant="default" class="navbar-horizontal" fixed="top">
        <div class="container">
          <router-link to="/" class="navbar-brand">Happy House</router-link>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <!-- Right aligned nav items -->
            <b-navbar-nav class="m-auto">
              <b-nav-form>
                <ul>
                  <li class="nav-item">
                    <router-link to="/notice" class="nav-link nav-link-icon">공지사항</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link to="/" class="nav-link">아파트 검색</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link to="/" class="nav-link">주변 탐방</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link to="/wish" class="nav-link">관심 지역</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link to="/qna" class="nav-link">Q&A</router-link>
                  </li>
                </ul>
              </b-nav-form>
            </b-navbar-nav>
            <b-navbar-nav class="m-auto">
              <b-nav-form>
                <ul>
                  <li class="nav-item">
                    <router-link to="/userinfo" class="nav-link">마이페이지</router-link>
                  </li>
                  <li class="nav-item">
                    <a @click="logout()" class="nav-link">LOGOUT</a>
                  </li>
                  <li v-if="user.userId == 'admin'" class="nav-item">
                    <router-link to="/adminuser" class="nav-link">회원 관리</router-link>
                  </li>
                </ul>
              </b-nav-form>
            </b-navbar-nav>
          </b-collapse>
        </div>
      </b-navbar>
    </div>
    <b-navbar
      v-else
      toggleable="lg"
      type="dark"
      variant="default"
      class="navbar-horizontal"
      fixed="top"
    >
      <div class="container">
        <router-link to="/" class="navbar-brand">Happy House</router-link>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <!-- Right aligned nav items -->
          <b-navbar-nav class="m-auto">
            <b-nav-form>
              <li class="nav-item">
                <router-link to="/notice" class="nav-link">공지사항</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/" class="nav-link">아파트 검색</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/qna" class="nav-link">Q&A</router-link>
              </li>
            </b-nav-form>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-form>
              <li class="nav-item">
                <router-link to="/register" class="nav-link">SIGN UP</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/login" class="nav-link">LOGIN</router-link>
              </li>
            </b-nav-form>
          </b-navbar-nav>
        </b-collapse>
      </div>
    </b-navbar>
    <!-- Navigation -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters(['user']),
  },
  methods: {
    logout() {
      this.$store.dispatch('resetUser');
      console.log(this.user.userId);
    },
  },
  created() {
    this.logout();
  },
};
</script>
