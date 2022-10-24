<template>
  <div>
    <b-card bg-variant="light">
      <b-form novalidate>
        <b-form-group
          label-cols-lg="3"
          label="Post"
          label-size="lg"
          label-class="font-weight-bold pt-0"
          class="mb-0"
        >

          <b-row class="my-1">
            <b-col sm="2">
              <label for="title">Title:</label>
            </b-col>
            <b-col sm="9">
              <b-form-input id="title" placeholder="Input title" v-model="data.title"></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
            <b-col sm="2">
              <label for="content">Content:</label>
            </b-col>
            <b-col sm="9">
              <b-form-textarea id="content" placeholder="Input content" v-model="data.content"></b-form-textarea>
            </b-col>
          </b-row> 
        </b-form-group>
        <b-row class="justify-content-center">
          <b-col cols="auto" >
            <b-button @click="save" variant="success">Save</b-button>
          </b-col>
        </b-row>
      </b-form>

    </b-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { postService, toastService } from '@/services';
import { Post } from '@/models';

@Component({
  components: {
   
  },
})
export default class PostEditPage extends Vue {
  private isEdit?: boolean = false;
  private data: Post = new Post();
  private id?: string = '';

  async created() {
    if(this.$router.currentRoute.params.id) {
      this.isEdit = true;
      this.id = this.$router.currentRoute.params.id;
      await postService.get(this.id).then((res) => { 
        if(res.data.data) {
          this.data = res.data.data;
        }
      })
    }
  }

  private save() {
    // update
    if(this.isEdit && this.data.id) {
      postService.update(this.data).then((res) => {
        if(res.status === 200) {
          toastService.successMessage('Update thành công!');
          this.$router.back();
        } else {
          toastService.errorMessage('Update thất bại!')
        }
      })
    } else {
      // save new Post
      postService.save(this.data).then((res) => {
        if(res.status === 200) {
          toastService.successMessage('Thêm thành công!');
          this.$router.back();
        } else {
          toastService.errorMessage('Thêm thất bại!')
        }
      })
    }
  }
}
</script>
<style scoped>
.column {
  float: left;
  width: 50%;
  padding: 10px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>