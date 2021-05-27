<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <!-- Nav tabs -->

        <ul class="nav nav-tabs">
          <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#tab1">회원정보 확인</a>
          </li>
        </ul>

        <!-- Tab panes -->

        <div class="tab-content">
          <div class="tab-pane container active pt-5" id="tab1">
            <form class="form-horizontal pt-5" id="loginform" action="" method="post">
              <div class="form-group has-feedback row">
                <label for="inputName" class="col-md-3 control-label text-md-right col-form-label"
                  >아이디
                </label>
                <div class="col-md-8">
                  <label for="inputName" class="control-label text-md-right col-form-label"
                    >{{ user.userId }}
                  </label>
                </div>
              </div>
              <div class="form-group has-feedback row">
                <label for="userPwd" class="col-md-3 control-label text-md-right col-form-label"
                  >비밀번호
                </label>
                <div class="col-md-8">
                  <label
                    v-if="!modify"
                    for="inputName"
                    class="col-md-3 control-label text-md-right col-form-label"
                    >{{ user.userPwd }}
                  </label>
                  <input
                    v-else
                    type="password"
                    class="form-control"
                    id="userPwd"
                    ref="userPwd"
                    v-model="user.userPwd"
                  />
                </div>
              </div>
              <div v-if="modify" class="form-group has-feedback row">
                <label for="pwdcheck" class="col-md-3 control-label text-md-right col-form-label"
                  >비밀번호 확인
                </label>
                <div class="col-md-8">
                  <input
                    type="password"
                    class="form-control"
                    id="pwdcheck"
                    ref="pwdcheck"
                    v-model="pwdcheck"
                    @keyup="checkPwd()"
                  />
                </div>
                <div v-if="msgPwd" id="waringMsg" class="mt-3" :class="statusPwdMsg">
                  {{ msgPwd }}
                </div>
              </div>
              <div class="form-group has-feedback row">
                <label
                  for="inputUserName"
                  class="col-md-3 control-label text-md-right col-form-label"
                  >이름
                </label>
                <div class="col-md-8">
                  <label
                    v-if="!modify"
                    for="inputName"
                    class="control-label text-md-right col-form-label"
                    >{{ user.userName }}
                  </label>
                  <input
                    v-else
                    type="text"
                    class="form-control"
                    id="userName"
                    ref="userName"
                    v-model="user.userName"
                  />
                </div>
              </div>
              <div class="form-group has-feedback row">
                <label for="inputEmail" class="col-md-3 control-label text-md-right col-form-label"
                  >주소
                </label>
                <div class="col-md-8">
                  <label
                    v-if="!modify"
                    for="inputName"
                    class="control-label text-md-right col-form-label"
                    >{{ user.address }}
                  </label>
                  <input
                    v-else
                    type="text"
                    class="form-control"
                    id="address"
                    ref="address"
                    v-model="user.address"
                  />
                </div>
              </div>
              <div class="form-group has-feedback row">
                <label
                  for="inputPassword"
                  class="col-md-3 control-label text-md-right col-form-label"
                  >이메일
                </label>
                <div class="col-md-8">
                  <label
                    v-if="!modify"
                    for="inputName"
                    class="control-label text-md-right col-form-label"
                    >{{ user.email }}
                  </label>
                  <input
                    v-else
                    type="text"
                    class="form-control"
                    id="email"
                    ref="email"
                    v-model="user.email"
                  />
                </div>
              </div>
            </form>
            <div class="row form-group">
              <div class="ml-md-auto col-md-9" v-if="!modify">
                <button class="btn btn-primary pr-3" @click="reverseModify()">
                  수정 <i class="fa fa-check pl-3"></i>
                </button>
                <button class="btn btn-danger pr-3" @click="del()">
                  탈퇴 <i class="fa fa-check pl-3"></i>
                </button>
              </div>
              <div class="ml-md-auto col-md-9" v-else>
                <button class="btn btn-primary pr-3" @click="update()">
                  완료 <i class="fa fa-check pl-3"></i>
                </button>
                <button class="btn btn-danger pr-3" @click="reverseModify()">
                  취소 <i class="fa fa-check pl-3"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';
export default {
  name: 'userinfo',
  data() {
    return {
      modify: false,
      pwdcheck: '',
      statusPwdMsg: '',
      msgPwd: '',
      okPwd: false,
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
  },
  methods: {
    reverseModify() {
      this.user.userPwd = '';
      this.pwdcheck = '';
      this.modify = !this.modify;
    },
    update() {
      let err = true;
      let msg = '';

      if (this.user.userName == '') {
        msg = '이름을 입력해주세요';
        err = false;
        this.$refs.userName.focus();
      }

      if (err && this.user.userPwd && !this.okPwd) {
        msg = '비밀번호를 확인해주세요';
        err = false;
        this.$refs.pwdcheck.focus();
      }

      if (!err) {
        alert(msg);
      } else {
        if (confirm('pwd : ' + this.user.userPwd)) {
          axios
            .put('http://localhost:8090/user/', {
              userName: this.user.userName,
              userId: this.user.userId,
              userPwd: this.user.userPwd,
              email: this.user.email,
              address: this.user.address,
            })
            .then(() => {
              alert('수정 성공');
              // this.$router.push('/userInfo');
              this.modify = !this.modify;
              this.user.userPwd = '';
              this.pwdcheck = '';
            })
            .catch(() => alert('수정 중 오류가 발생했습니다. 다시 시도해 주세요.'));
        }
      }
    },
    checkPwd() {
      if (this.user.userPwd && this.user.userPwd == this.pwdcheck) {
        this.msgPwd = '비밀번호가 일치합니다.';
        this.statusPwdMsg = 'okMsg';
        this.okPwd = true;
      } else {
        this.msgPwd = '비밀번호가 일치하지 않습니다.';
        this.statusPwdMsg = 'waringMsg';
        this.okPwd = false;
      }
    },

    del() {
      if (confirm('정말 탈퇴하시겠습니까?')) {
        axios
          .delete('http://localhost:8090/user')
          .then(({ data }) => {
            if (data) {
              alert(data);
              this.$router.push('/login'); // 메인으로 이동
            } else {
              alert('탈퇴 되었습니다.');
              this.$router.push('/'); // 메인으로 이동
            }
          })
          .catch(() => {
            alert('탈퇴 중 오류가 발생했습니다. 다시 시도해 주세요.');
          });
      }
    },
  },
};
</script>

<style scoped>
.waringMsg {
  color: red;
  font-size: 17px;
}

.okMsg {
  color: rgb(105, 209, 0);
  font-size: 17px;
}
</style>
