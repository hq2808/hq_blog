<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
// Import Main styles for this application
@import 'assets/scss/style';

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
@import url('https://fonts.googleapis.com/css?family=Roboto+Condensed');

html, body {
  font-family: 'Roboto', sans-serif;
}

#app {
  font-family: 'Roboto', sans-serif;
}
</style>
