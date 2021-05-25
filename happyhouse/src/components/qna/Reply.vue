<template>
  
    <div class="container">
    <h2 class="text-center">답변 등록</h2>
    <table class=" table table-condensed w-25 ">
      <tr>
        <th >내용</th>
        <td>
          <textarea           id="reply"  ref='reply'  cols="30" rows="10" v-model='reply'></textarea>
        </td>
      </tr>
    </table>
    <div  class='text-right'>
      <button class='btn btn-primary' @click="replyHandler">등록</button>
      <button class='btn btn-primary' @click="moveHandler">목록</button>
    </div>
    </div>
</template>
<script>
import axios from 'axios';
import {bus} from '@/eventbus'

export default {
    data() {
    return {
      no:"",
      title:"",
      writer:"",
      regtime:"",
      content:"",
      reply:"",
      }
    },
    methods: {
        moveHandler() {
        this.$router.push('/qna/moveqna/');
        },
        replyHandler(){
          let err = true;
          let msg = '';
        
          if(err && (this.reply==''||this.reply==null)){
            msg='답변을 입력해 주세요'
            err=false;
            this.$refs.reply.focus();
          }
          console.log(this.reply);
          if(!err){
            alert(msg);
          } else {
            axios.put('http://localhost:8090/qna/reply/', {
              no: this.no,
              title: this.title,
              writer: this.writer,
              content: this.content,
              reply: this.reply
            })
            .then(({ data }) => {
              console.log(data);
              if (data == "success") {
                alert("답변 완료!");
                this.$router.push('/qna/search');
                setTimeout(()=>{
                  bus.$emit('searchBoard',this.no)
                },100);
              } else {
                alert("답변중 오류 발생!");
              }
            })
            .catch(err => {
              console.log(err);
              alert("답변 등록중 오류 발생!");
            });
          }
        },
        setBoard(data){
            this.no = data.no;
            this.title = data.title;
            this.writer = data.writer;
            this.regtime = data.regtime;
            this.content = data.content;
            this.reply = data.reply;
        }
      },
      created() {
        bus.$on('replyBoard',this.setBoard);
      }
}
</script>
