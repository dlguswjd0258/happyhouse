<template>
  <div class="container">
    <div class="text-right">
      <button class="btn btn-primary" @click="movePage">등록</button>
    </div>
    <div v-if="notices.length > 0">
      <b-table-simple hover small borderless>
        <colgroup>
          <col width="5%" />
          <col width="45%" />
          <col width="15%" />
          <col width="25%" />
          <col width="10%" />
        </colgroup>
        <b-thead>
          <b-th>번호</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>날짜</b-th>
        </b-thead>
        <b-tbody v-for="(notice, index) in notices" :key="index">
          <b-tr>
            <b-td>{{ notice.no }}</b-td>
            <b-td v-text="notice.title" @click="searchNotice(notice.no)"></b-td>
            <b-td v-text="notice.writer"></b-td>
            <b-td>{{ notice.regtime | toDate }}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
    <div v-else>게시글이 없습니다.</div>
  </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';
import { bus } from '@/eventbus';

export default {
  data() {
    return { notices: [] };
  },
  filters: {
    toDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
  methods: {
    searchNotice(no) {
      this.$router.push('/notice/detail');
      setTimeout(() => {
        bus.$emit('searchNotice', no);
      }, 100);
    },
    movePage() {
      this.$router.push('/notice/create');
    },
  },
  created() {
    axios
      .get('http://localhost:8090/notice')
      .then(({ data }) => {
        this.notices = data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
