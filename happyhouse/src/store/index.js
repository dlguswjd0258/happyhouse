import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
  },
  getters: {
    user(state) {
      return state.user;
    },
  },
  mutations: {
    setUser(state, payload) {
      state.user = payload;
    },
  },
  actions: {
    getUser(context) {
      axios
        .get('http://localhost:8090/user')
        .then(({ data }) => {
          context.commit('setUser', data);
        })
        .catch(() => alert('사용자 정보 저장 중 오류가 발생했습니다.'));
    },
  },
  modules: {},
});
