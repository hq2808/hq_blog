<template>
  <div class="category">123
     <img
      v-if="product === 'headphones'"
      src="../../assets/shared/desktop/image-category-thumbnail-headphones.png"
      alt="headphones"
      class="category__thumbnail"
      id="headphones"
    />
  </div>
</template>

<script lang="ts">
import {Component, Vue, Prop} from 'vue-property-decorator';

@Component
export default class Category extends Vue {
  @Prop() product!: string;
  @Prop() show!: boolean;
}
</script>
