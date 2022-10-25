import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
    timeSignin: null,
    otp: null,
    otpId: null,
  },
  mutations: {
    SET_USER(state, payload) {
      state.user = payload;
    },
    SET_TIME_SIGN_IN(state, payload) {
      state.timeSignin = payload;
    },
    SET_OTP(state, payload) {
      state.otp = payload;
    },
    SET_OTP_ID(state, payload) {
      state.otpId = payload;
    },
  },
  actions: {
    setUser({ commit }, payload) {
      commit('SET_USER', payload);
    },
    setLocale({ commit }, payload) {
      commit('SET_LOCALE', payload);
    },
    setTimeSignin({ commit }, payload) {
      commit('SET_TIME_SIGN_IN', payload);
    },
  },
});
