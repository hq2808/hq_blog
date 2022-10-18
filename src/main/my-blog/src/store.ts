import Vue from 'vue';
import Vuex from 'vuex';
// import mainApp from './main';
// import createPersist from 'vuex-localstorage';

Vue.use(Vuex);

export default new Vuex.Store({
  // plugins: [createPersist({
  //   namespace: 'nc.tirecloak',
  //   initialState: {},
  // })],
  state: {
    // init default lang
    storedLang: 'ja',
    user: {},
    locale: 'ja',
    timeSignin: null,
    otp: null,
    otpId: null,
  },
  mutations: {
    // SET_LANG(state, payload) {
    //   mainApp.$i18n.locale = payload;
    //   state.storedLang = payload;
    // },
    SET_USER(state, payload) {
      state.user = payload;
    },
    SET_LOCALE(state, payload) {
      state.locale = payload;
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
    setLang({ commit }, payload) {
      commit('SET_LANG', payload);
    },
    setUser({ commit }, payload) {
      commit('SET_USER', payload);
    },
    setLocale({ commit }, payload) {
      commit('SET_LOCALE', payload);
    },
    setTimeSignin({ commit }, payload) {
      commit('SET_TIME_SIGN_IN', payload);
    },
    setOTP({ commit }, payload) {
      commit('SET_OTP', payload);
    },
    setOTPID({ commit }, payload) {
      commit('SET_OTP_ID', payload);
    },
  },
});
