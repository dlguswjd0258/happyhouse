<template>
  <div class="container" align="center">
    <input type="hidden" name="pageNo" id="pageNo" />
    <div class="form-inline mb-3" align="right">
      <b-form-select v-model="bean.key" class="form-group col-sd-2 mr-3">
        <b-form-select-option value="all">--선택하세요--</b-form-select-option>
        <b-form-select-option value="userid">아이디</b-form-select-option>
        <b-form-select-option value="username">이름</b-form-select-option>
      </b-form-select>
      <input type="text" class="form-group col-sd-2m mr-3" style="display: none" />
      <input
        type="text"
        class="form-control mr-3 col-sd-2"
        placeholder="Enter search word"
        id="word"
        name="word"
        v-model="bean.word"
      />
      <button type="button" class="form-group btn btn-primary" @click="search()">검색</button>
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
            ><button type="button" class="btn btn-primary" @click="deleteUser(user.userId)">
              삭제
            </button></b-td
          >
        </b-tr>
      </b-tbody>
      <b-tfoot id="paging">
        <div class="overflow-auto">
          <b-pagination
            v-model="pageNo"
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
import { mapGetters } from 'vuex';
export default {
  data() {
    return {
      users: [],
      bean: {},
      pageNo: 1,
    };
  },
  computed: {
    ...mapGetters(['user']),
  },
  created() {
    if (!this.user) {
      alert('로그인 해주세요.');
      this.$router.push('/login');
    }
    this.getList();
  },
  methods: {
    getList() {
      axios
        .get(`http://localhost:8090/user/${this.pageNo}/all/${null}`)
        .then(({ data }) => {
          this.users = data.users;
          this.bean = data.bean;
          this.bean.word = '';
        })
        .catch(() => alert('회원 조회 중 오류가 발생했습니다.'));
    },
    search() {
      this.pageNo = 1;
      if (!this.bean.word) {
        axios
          .get(`http://localhost:8090/user/${this.pageNo}/all/${null}`)
          .then(({ data }) => {
            this.users = data.users;
            this.bean = data.bean;
            this.bean.word = '';
          })
          .catch(() => alert('회원 조회 중 오류가 발생했습니다.'));
      } else {
        axios
          .get(`http://localhost:8090/user/${this.pageNo}/${this.bean.key}/${this.bean.word}`)
          .then(({ data }) => {
            this.users = data.users;
            this.bean = data.bean;
            this.bean.word = '';
          })
          .catch(() => alert('회원 조회 중 오류가 발생했습니다.'));
      }
    },
    deleteUser(userid) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios
          .delete('http://localhost:8090/user/' + userid)
          .then(() => {
            alert('삭제 되었습니다.');
            this.getList();
          })
          .catch(() => {
            alert('삭제 중 오류가 발생했습니다. 다시 시도해 주세요.');
          });
      }
    },
  },
};
</script>
