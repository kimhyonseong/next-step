String.prototype.format = function() {
  let args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(document).ready(function(){/* jQuery toggle layout */
  $('#btnToggle').click(function(){
    if ($(this).hasClass('on')) {
      $('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
      $(this).removeClass('on');
    }
    else {
      $('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
      $(this).addClass('on');
    }
  });

  $(".addAnswer").click(addAnswer);
  $(".qna-comment").on("click",".link-delete-article",deleteAnswer);
});

function addAnswer(e) {
  e.preventDefault();

  const queryString = $("form[name=answer]").serialize();
  console.info(queryString);

  $.ajax({
    type:`post`,
    url:`/api/qna/addAnswer`,
    data:queryString,
    dataType:`json`,
    error:(e) => console.log(e),
    success: (data) => addAnswerSuccess(data,null)
  })
}

function addAnswerSuccess(json, status) {
  console.log(json);
  const answerTemplate = $("#answerTemplate").html();
  const template = answerTemplate.format(
      json.answer.writer,
      new Date(json.answer.createdDate),
      json.answer.contents,
      json.answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
  $("input[name=writer]").val("");
  $("textarea[name=contents]").val("");
}

function deleteAnswer(e) {
  e.preventDefault();

  if (confirm("정말 삭제하시겠습니까?")) {
    const queryString = $(".form-delete").serialize();
    const target = $(this);

    $.ajax({
      type: `post`,
      url: `/api/qna/deleteAnswer`,
      data: queryString,
      dataType: `json`,
      error: (e) => {
        console.error(e);
        alert("오류로 인해 댓글이 삭제되지 않았습니다.");
      },
      success: (data) => deleteAnswerSuccess(data,target)
    })
  }
}

function deleteAnswerSuccess(json, target) {
  console.log(json);
  if (json.result.status) {
    target.closest(".article").remove();
    alert("댓글이 삭제되었습니다.");
  } else {
    alert("오류로 인해 댓글이 삭제되지 않았습니다.");
  }
}

function questionList() {
  let template = `<li>
                  <div class="wrap">
                      <div class="main">
                          <strong class="subject">
                              <a href="/qna/show?questionId=QUESTION_ID">TITLE</a>
                          </strong>
                          <div class="auth-info">
                              <i class="icon-add-comment"></i>
                              <span class="time">
                                  <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="CREATE_DATE" />
                              </span>
                              <a href="user/profile.jsp" class="author">WRITER</a>
                          </div>
                          <div class="reply" title="댓글">
                              <i class="icon-reply"></i>
                              <span class="point">COUNT_ANSWER</span>
                          </div>
                      </div>
                  </div>
              </li>`;
  let html = ``;
  $.ajax({
    url: `/api/qna/list`,
    method: `get`,
    dataType: `json`,
    error: (e) => {console.log(e)},
    success: (data) => {
      console.log(data.questions);
      for(let i=0; i<data.questions.length; i++) {
        html += template.replace("QUESTION_ID",data.questions[i].questionId)
            .replace("TITLE",data.questions[i].title)
            .replace("WRITER",data.questions[i].writer)
            .replace("CREATE_DATE",data.questions[i].createdDate)
            .replace("COUNT_ANSWER",data.questions[i].countOfAnswer)
      }
      $(".list").html(html);
    }
  })
}