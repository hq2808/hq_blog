<template>
  <div>
    <b-card
      :title="item.title"
      img-src="https://picsum.photos/600/300/?image=25"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 100%;"
      class="mb-2"
    >
    <b-card-text>
      {{item.content}}
    </b-card-text>

      <b-button href="#" variant="primary">Go somewhere</b-button>
    </b-card>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';

@Component
export default class ItemCard extends Vue {
  @Prop() item: any;
}
</script>
