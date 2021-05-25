<template>
  <div class="container" align="center">
    <input type="hidden" name="pageNo" id="pageNo" />
    <div class="mb-3" align="right">
      <b-form-select v-model="bean.key" class="col-sd-2">
        <b-form-select-option value="all">--선택하세요--</b-form-select-option>
        <b-form-select-option value="userid">아이디</b-form-select-option>
        <b-form-select-option value="username">이름</b-form-select-option>
      </b-form-select>
      <input type="text" class="col-sd-2" style="display: none" />
      <input
        type="text"
        class="col-sd-2"
        placeholder="Enter search word"
        id="word"
        name="word"
        v-model="bean.word"
      />
      <button type="button" class="btn btn-primary" @click="search()">검색</button>
    </div>
    <b-table-simple hover small borderless>
      <colgroup>
        <col width="120" />
        <col width="120" />
        <col width="120" />
        <col width="170" />
        <col width="*" />
        <col width="120" />
        <col width="130" />
      </colgroup>
      <b-thead>
        <b-tr>
          <b-th class="text-center">아이디</b-th>
          <b-th class="text-center">이름</b-th>
          <b-th class="text-center">이메일</b-th>
          <b-th class="text-center">주소</b-th>
          <b-th class="text-center">가입일</b-th>
          <b-th class="text-center">수정/삭제</b-th>
        </b-tr>
      </b-thead>
      <b-tbody id="userlist">
        <b-tr v-if="!users">등록된 회원이 없습니다.</b-tr>
        <b-tr v-for="(user, idex) in users" :key="idex">
          <b-td class="text-center" v-text="user.userId"></b-td>
          <b-td class="text-center" v-text="user.userName"></b-td>
          <b-td class="text-center" v-text="user.email"></b-td>
          <b-td class="text-center" v-text="user.address"></b-td>
          <b-td class="text-center" v-text="user.joindate"></b-td>
          <b-td class="text-center"
            ><button type="button" class="btn btn-primary">삭제</button></b-td
          >
        </b-tr>
      </b-tbody>
      <b-tfoot id="paging">
        <div class="overflow-auto">
          <b-pagination
            v-model="bean.pageNo"
            :total-rows="bean.total"
            :per-page="bean.interval"
            aria-controls="my-table"
          ></b-pagination>
        </div>
        <b-tr align="center">
          <b-td colspan="7" v-html="bean.pageLink"></b-td>
        </b-tr>
      </b-tfoot>
    </b-table-simple>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      users: [],
      bean: {},
    };
  },
  methods: {
    search() {
      if (!this.bean.word) {
        alert('검색할 내용을 입력하세요.');
      } else {
        axios
          .get(`http://localhost:8090/user/1/${this.bean.key}/${this.bean.word}`)
          .then(({ data }) => {
            this.users = data.users;
            this.bean = data.bean;
            this.bean.word = '';
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
  created() {
    axios
      .get(`http://localhost:8090/user/1/all/${null}`)
      .then(({ data }) => {
        this.users = data.users;
        this.bean = data.bean;
        this.bean.word = '';
      })
      .catch(() => alert('회원 조회 중 오류가 발생했습니다.'));
  },
};
</script>
