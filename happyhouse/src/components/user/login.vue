<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <h2>로그인</h2>
        <div class="form-horizontal pt-5" id="loginform">
          <div class="form-group" align="left">
            <label for="">아이디</label>
            <input type="text" class="form-control" id="userId" ref="userId" v-model="userId" />
          </div>
          <div class="form-group" align="left">
            <label for="">비밀번호</label>
            <input
              type="password"
              class="form-control"
              id="userPwd"
              ref="userPwd"
              v-model="userPwd"
              @keypress.enter="login()"
            />
            <div v-if="msg" class="errorMsg mt-3">{{ msg }}</div>
          </div>

          <div class="form-group" align="center">
            <button type="button" class="btn btn-warning" @click="login()">로그인</button>
            <button type="button" class="btn btn-primary" @click="moveJoin()">회원가입</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return { userId: '', userPwd: '', msg: '' };
  },
  methods: {
    login() {
      let err = true;

      if (this.userId == '') {
        this.msg = '아이디를 입력해 주세요';
        err = false;
        this.$refs.userId.focus();
      }
      if (err && this.userPwd == '') {
        this.msg = '비밀번호를 입력해 주세요';
        err = false;
        this.$refs.userPwd.focus();
      }

      if (err) {
        axios
          .post('http://localhost:8090/user/login', {
            userId: this.userId,
            userPwd: this.userPwd,
          })
          .then(({ data }) => {
            console.log(data);
            if (data == 'success') {
              // this.msg = data;
              this.$store.dispatch('getUser');
              this.$router.push('/');
            } else {
              this.msg = data;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    moveJoin() {
      this.$router.push('/register');
    },
  },
};
</script>

<style>
.errorMsg {
  color: red;
  font-size: 17px;
}
</style>
