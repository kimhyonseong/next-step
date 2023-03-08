String.prototype.format = function() {
  var args = arguments;
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
    error:(e) => {
      console.log(e);
    },
    success: (e) => {
      console.log(e);
      addAnswerSuccess(e,null);
    }
  })
}

function addAnswerSuccess(json, status) {
  const answerTemplate = $("#answerTemplate").html();
  const template = answerTemplate.format(
      json.writer,
      new Date(json.createdDate),
      json.contents,
      json.answerId);
  console.log(`json.answerId : ${json.answerId}`)
  $(".qna-comment-slipp-articles").prepend(template);
}