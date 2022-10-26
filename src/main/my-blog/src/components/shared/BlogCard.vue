<template>
  <div class="d-flex border-bottom">
    <b-row>
      <b-col cols="1">
        <img src="https://mdbcdn.b-cdn.net/img/new/avatars/1.webp" class="rounded-circle shadow-4"
          style="width: 50px;" alt="Avatar" />
      </b-col>
      <b-col cols="10">
        <h5><a @click="readMore(item.id)" class="text-decoration-none">{{item.title}}</a></h5>
      </b-col>
      <b-col cols="1">
        <b-button v-if="isAuth" @click="goToDetail(item.id)" variant="success">Sửa</b-button>
      </b-col>
    </b-row>

    <!-- <div>
      avatar | 
    </div>
    <div class="ml-1">
      {{item.title}}
    </div> -->
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';

@Component
export default class BlogCard extends Vue {
  @Prop() item: any;
  @Prop() index!: number;
  private isAuth?: boolean = false;

  private created() {
    if(this.item.user.username === this.$store.state.user.username) {
      this.isAuth = true
    }
  }

  private goToDetail(id: any) {
    this.$emit('goToDetail', id);
  }

  private readMore(id: any) {
    this.$emit('readMore', id);
  }
}
</script>
<style lang="scss" scoped>

</style>