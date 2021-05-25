<template>
  <div class="container" align="center">
    <input type="hidden" name="pageNo" id="pageNo" />
    <div class="mb-3" align="right">
      <b-form-select v-model="key" class="col-sd-2">
        <b-form-select-option :value="all">--선택하세요--</b-form-select-option>
        <b-form-select-option :value="userId">아이디</b-form-select-option>
        <b-form-select-option :value="userName">이름</b-form-select-option>
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
      <a href="#" class="btn btn-primary col-sd-2" id="btn">검색</a>
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
            :total-rows="bean.interval"
            :per-page="perPage"
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
// import axios from 'axios';
export default {
  data() {
    return {
      users: [],
      bean: {},
    };
  },
  methods: {
    makeList() {
      // $('#userlist').empty();
      // $('#paging').empty();
      // if (map.users != null) {
      //   $(map.users).each(function (index, user) {
      //     let str = `
      // 	<tr id="view_${'${user.userId}'}" class="view" data-id="${'${user.userId}'}">
      // 		<td>${'${user.userId}'}</td>
      // 		<td>${'${user.userPwd}'}</td>
      // 		<td>${'${user.userName}'}</td>
      // 		<td>${'${user.email}'}</td>
      // 		<td>${'${user.address}'}</td>
      // 		<td>${'${user.joindate}'}</td>
      // 		<td>
      // 			<button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
      // 			<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
      // 		</td>
      // 	</tr>
      // 	<tr id="mview_${'${user.userId}'}" data-id="${'${user.userId}'}" style="display: none;">
      // 		<td>${'${user.userId}'}</td>
      // 		<td><input type="text" name="userPwd" id="userPwd${'${user.userId}'}" value="${'${user.userPwd}'}"></td>
      // 		<td>${'${user.userName}'}</td>
      // 		<td><input type="text" name="email" id="email${'${user.userId}'}" value="${'${user.email}'}"></td>
      // 		<td><input type="text" name="address" id="address${'${user.userId}'}" value="${'${user.address}'}"></td>
      // 		<td>${'${user.joindate}'}</td>
      // 		<td>
      // 			<button type="button" class="modifyBtn btn btn-primary btn-sm">수정</button>
      // 			<button type="button" class="cancelBtn btn btn-danger btn-sm">취소</button>
      // 		</td>
      // 	</tr>
      // 	`;
      //     $('#userlist').append(str);
      //   }); //each
      // } else {
      //   let str = `
      // <tr align="center">
      // 	<td colspan="7">회원 정보가 없습니다.</td>
      // </tr>
      // `;
      //   $('#userlist').append(str);
      // }
    },
  },
  beforeCreate() {},
  created() {
    this.bean.word = null;
    axios.get(`http://localhost:8090/user/1/all/${this.bean.word}`).then(({ data }) => {
      this.users = data.users;
      this.bean = data.bean;
    });
  },
};
// $(document).ready(function () {
//   //회원 목록
//   $.ajax({
//     url: '${root}/user/1/all/""',
//     type: 'GET',
//     success: function (map) {
//       makeList(map);
//     },
//     error: function (xhr, status, msg) {
//       console.log('상태값 : ' + status + ' Http에러메시지 : ' + msg);
//     },
//     statusCode: {
//       500: function () {
//         alert('서버에러.');
//         // location.href = "/error/e500";
//       },
//       404: function () {
//         alert('페이지없다.');
//         // location.href = "/error/e404";
//       },
//     },
//   });

//   $(document).on('dblclick', 'tr.view', function () {
//     let vid = $(this).attr('data-id');
//     $.ajax({
//       url: '${root}/user/' + vid,
//       type: 'GET',
//       contentType: 'application/json;charset=utf-8',
//       success: function (user) {
//         $('#vid').text(user.userId);
//         $('#vname').text(user.userName);
//         $('#vemail').text(user.email);
//         $('#vaddress').text(user.address);
//         $('#vjoindate').text(user.joindate);
//         // $("#userViewModal").modal();
//       },
//       error: function (xhr, status, msg) {
//         console.log('상태값 : ' + status + ' Http에러메시지 : ' + msg);
//       },
//     });
//   });

//   /* // 회워 정보 수정 보기.
// 		$(document).on("click", ".modiBtn", function() {
// 			let mid = $(this).parents("tr").attr("data-id");
// 			$("#view_" + mid).css("display", "none");
// 			$("#mview_" + mid).css("display", "");
// 		});

// 		// 회워 정보 수정 실행.
// 		$(document).on("click", ".modifyBtn", function() {
// 			let mid = $(this).parents("tr").attr("data-id");
// 			let modifyinfo = JSON.stringify({
// 						"userId" : mid,
// 						"userPwd" : $("#userPwd" + mid).val(),
// 						"email" : $("#email" + mid).val(),
// 						"address" : $("#address" + mid).val()
// 					   });
// 			$.ajax({
// 				url:'${root}/admin/user',
// 				type:'PUT',
// 				contentType:'application/json;charset=utf-8',
// 				dataType:'json',
// 				data: modifyinfo,
// 				success:function(users) {
// 					makeList(users);
// 				},
// 				error:function(xhr,status,msg){
// 					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
// 				}
// 			});
// 		});

// 		// 회워 정보 수정 취소.
// 		$(document).on("click", ".cancelBtn", function() {
// 			let mid = $(this).parents("tr").attr("data-id");
// 			$("#view_" + mid).css("display", "");
// 			$("#mview_" + mid).css("display", "none");
// 		});

// 		// 회워 탈퇴.
// 		$(document).on("click", ".delBtn", function() {
// 			if(confirm("정말 삭제?")) {
// 				let delid = $(this).parents("tr").attr("data-id");
// 				$.ajax({
// 					url:'${root}/admin/user/' + delid,
// 					type:'DELETE',
// 					contentType:'application/json;charset=utf-8',
// 					dataType:'json',
// 					success:function(users) {
// 						makeList(users);
// 					},
// 					error:function(xhr,status,msg){
// 						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
// 					}
// 				});
// 			}
// 		}); */
// });
</script>
