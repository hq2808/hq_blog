import Vue from 'vue';
import Vuex from 'vuex';

import createPersist from 'vuex-localstorage';

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersist({
    namespace: 'hq.blog',
    initialState: {},
  })],
  state: {
    user: {},
    timeSignin: null,
  },
  mutations: {
    SET_USER(state, payload) {
      state.user = payload;
    },
    SET_TIME_SIGN_IN(state, payload) {
      state.timeSignin = payload;
    },
  },
  actions: {
    setUser({ commit }, payload) {
      commit('SET_USER', payload);
    },
    setTimeSignin({ commit }, payload) {
      commit('SET_TIME_SIGN_IN', payload);
    },
  },
});
