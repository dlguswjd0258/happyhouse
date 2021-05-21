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
                  <label for="inputName" class="col-md-3 control-label text-md-right col-form-label"
                    >{{ user.userId }}
                  </label>
                </div>
              </div>
              <div class="form-group has-feedback row">
                <label for="userPwd" class="col-md-3 control-label text-md-right col-form-label"
                  >비밀번호
                </label>
                <div class="col-md-8">
                  <input
                    type="password"
                    class="form-control"
                    id="userPwd"
                    ref="userPwd"
                    v-model="user.userPwd"
                  />
                </div>
              </div>
              <div class="form-group has-feedback row">
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
                  />
                </div>
              </div>
              <div class="form-group has-feedback row">
                <label
                  for="inputUserName"
                  class="col-md-3 control-label text-md-right col-form-label"
                  >이름
                </label>
                <div class="col-md-8">
                  <input
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
                  <input
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
                  <input
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
              <div class="ml-md-auto col-md-9">
                <button class="btn btn-primary pr-3" @click="update()">
                  수정 <i class="fa fa-check pl-3"></i>
                </button>
                <button class="btn btn-danger pr-3" @click="del()">
                  탈퇴 <i class="fa fa-check pl-3"></i>
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
      pwdcheck: '',
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
    console.log(this.user);
  },
  methods: {
    update() {
      //   if ($('#userName').val() == '') {
      //     alert('이름 입력!!!');
      //     return;
      //   } else if ($('#userPwd').val() == '') {
      //     alert('비밀번호 입력!!!');
      //     return;
      //   } else if ($('#userPwd').val() != $('#pwdcheck').val()) {
      //     alert('비밀번호 확인!!!');
      //     return;
      //   } else if ($('address').val() == '') {
      //     alert('주소 입력!!!');
      //     return;
      //   } else if ($('email').val() == '') {
      //     alert('이메일 입력!!!');
      //     return;
      //   } else {
      //     $('#loginform').action = '${root}/user/modify';
      //     $('#loginform').submit();
      //   }
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

<style></style>
