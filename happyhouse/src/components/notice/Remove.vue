<template>
  <div>삭제중...</div>
</template>
<script>
import axios from 'axios';
import { bus } from '@/eventbus';

export default {
  created() {
    bus.$on('removeBoard', this.removeBoard);
  },
  methods: {
    removeBoard(no) {
      axios
        .delete(`http://localhost:8090/notice/${no}`)
        .then(({ data }) => {
          if (data == 'success') {
            alert('삭제완료!');
            this.$router.push('/notation');
          } else {
            alert('삭제중 오류!');
            this.$router.push('/notation/search');
            setTimeout(() => {
              bus.$emit('searchBoard', no);
            }, 100);
          }
        })
        .catch((err) => {
          console.log(err);
          alert('게시글 수정중 오류 발생!');
        });
    },
  },
};
</script>
