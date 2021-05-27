<template>
  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <h2>회원 가입</h2>
        <!-- Tab panes -->
        <div class="tab-content">
          <div class="tab-pane container active pt-5" id="tab1">
            <form class="form-horizontal" id="memberform" method="post" action="">
              <div class="form-group" align="left">
                <label for="name">이름</label>
                <input
                  type="text"
                  class="form-control"
                  id="userName"
                  ref="userName"
                  v-model="userName"
                />
              </div>
              <div class="form-group" align="left">
                <label for="userId">아이디</label>
                <div class="form-inline">
                  <input
                    type="text"
                    class="form-control mr-3"
                    id="userId"
                    ref="userId"
                    v-model="userId"
                    style="width: 80%"
                  />
                  <input
                    type="button"
                    class="form-control"
                    style="width: 15%"
                    @click="checkId()"
                    value="중복 확인"
                  />
                </div>
                <div v-if="msgId" id="waringMsg" class="mt-3" :class="statusIdMsg">
                  {{ msgId }}
                </div>
              </div>
              <div class="form-group" align="left">
                <label for="userPwd">비밀번호</label>
                <input
                  type="password"
                  class="form-control"
                  id="userPwd"
                  ref="userPwd"
                  v-model="userPwd"
                  @keyup="checkPwd()"
                />
              </div>
              <div class="form-group" align="left">
                <label for="pwdcheck">비밀번호 확인</label>
                <input
                  type="password"
                  class="form-control"
                  id="pwdcheck"
                  ref="pwdcheck"
                  v-model="pwdcheck"
                  @keyup="checkPwd()"
                />
                <div v-if="msgPwd" id="waringMsg" class="mt-3" :class="statusPwdMsg">
                  {{ msgPwd }}
                </div>
              </div>
              <div class="form-group" align="left">
                <label for="emailId">이메일</label><br />
                <div class="custom-control-inline">
                  <input
                    type="text"
                    class="form-control"
                    id="emailId"
                    ref="emailId"
                    v-model="emailId"
                    size="50"
                  />
                  &nbsp; @ &nbsp;
                  <select class="form-control" id="emailDomain" v-model="emailDomain">
                    <option value="naver.com">naver.com</option>
                    <option value="google.com">google.com</option>
                    <option value="daum.net">daum.net</option>
                    <option value="nate.com">nate.com</option>
                    <option value="hanmail.net">hanmail.net</option>
                  </select>
                </div>
              </div>

              <div class="form-group" align="left">
                <label for="addressCommon">주소</label><br />
                <div>
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">{{ postcode || '우편번호' }}</span>
                    </div>
                    <input
                      v-model="address"
                      type="text"
                      class="form-control"
                      placeholder="주소"
                      readonly
                    />
                    <div class="input-group-append">
                      <button @click="openModal()" class="form-control" type="button">
                        주소검색
                      </button>
                    </div>
                  </div>

                  <div class="input-group mb-3">
                    <input
                      v-model="addressDetail"
                      type="text"
                      class="form-control"
                      placeholder="상세주소"
                    />
                  </div>
                </div>
                <post-modal @close="closeModal" v-if="showModal"> </post-modal>
              </div>
              <div class="form-group" align="center">
                <button type="button" class="btn btn-primary" id="registerBtn" @click="regist()">
                  회원가입
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { bus } from '@/eventbus';
import PostModal from '@/components/user/PostModal';
export default {
  components: {
    PostModal,
  },
  data() {
    return {
      userName: '',
      userId: '',
      userPwd: '',
      pwdcheck: '',
      emailId: '',
      emailDomain: 'naver.com',
      postcode: '',
      address: '',
      addressDetail: '',
      okId: false,
      okPwd: false,
      msgId: '',
      msgPwd: '',
      statusIdMsg: '',
      statusPwdMsg: '',
      showModal: false,
    };
  },
  created() {
    bus.$on('getAddress', this.setAddress);
  },
  methods: {
    openModal() {
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    regist() {
      let err = true;
      let msg = '';

      if (this.userName == '') {
        msg = '이름을 입력해주세요';
        err = false;
        this.$refs.userName.focus();
      }
      if (err && this.userId == '') {
        msg = '아이디를 입력해주세요';
        err = false;
        this.$refs.userId.focus();
      }

      if (err && !this.okId) {
        msg = '아이디 중복확인을 해주세요';
        err = false;
        this.$refs.userId.focus();
      }

      if (err && this.userPwd == '') {
        msg = '비밀번호를 입력해주세요';
        err = false;
        this.$refs.userPwd.focus();
      }

      if (err && !this.okPwd) {
        msg = '비밀번호를 확인해주세요';
        err = false;
        this.$refs.pwdcheck.focus();
      }

      if (!err) {
        alert(msg);
      } else {
        axios
          .post('http://localhost:8090/user/', {
            userName: this.userName,
            userId: this.userId,
            userPwd: this.userPwd,
            email: this.emailId + '@' + this.emailDomain,
            address: this.address + ' ' + this.addressDetail,
          })
          .then(() => {
            alert('회원가입 성공');
            this.$router.push('/login');
          });
      }
    },
    checkId() {
      if (this.userId == '') {
        alert('아이디를 입력해주세요');
        this.$refs.userId.focus();
      } else {
        axios.get('http://localhost:8090/user/' + this.userId).then(({ data }) => {
          if (data) {
            this.msgId = data;
            this.statusIdMsg = 'waringMsg';
            this.okId = false;
          } else {
            this.statusIdMsg = 'okMsg';
            this.msgId = '사용 가능한 아이디입니다.';
            this.okId = true;
          }
        });
      }
    },
    checkPwd() {
      if (this.userPwd && this.userPwd == this.pwdcheck) {
        this.msgPwd = '비밀번호가 일치합니다.';
        this.statusPwdMsg = 'okMsg';
        this.okPwd = true;
      } else {
        this.msgPwd = '비밀번호가 일치하지 않습니다.';
        this.statusPwdMsg = 'waringMsg';
        this.okPwd = false;
      }
    },
    setAddress(data) {
      console.log(data);
      this.postcode = data.zonecode;
      this.address = data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress;
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
