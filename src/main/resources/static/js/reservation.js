var main = {
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){
           _this.save();
        });

        $('#btn-update').on('click', function(){
            _this.changeTime();
        });

        $('#btn-cancel').on('click', function(){
            _this.cancel();
        });
    },
    save : function(){
        var data = {
            serviceType: $('#serviceType').val(),
            startTime: $('#reserveDate').val()+"T"+$('#startTime').val(),
            endTime: $('#reserveDate').val()+"T"+$('#endTime').val(),
            pet: { id: $('#pet').val() }
        };
        console.log(JSON.stringify(data));

        $.ajax({
            type: 'POST',
            url: '/api/reservations',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('예약요청 되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    changeTime : function(){
        var data = {
            startTime: $('#reserveDate').val()+"T"+$('#startTime').val(),
            endTime: $('#reserveDate').val()+"T"+$('#endTime').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/reservations/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('예약시간이 변경되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    cancel :  function(){
        var id = $('#id').val();

        $.ajax({
            type: 'PATCH',
            url: '/api/reservations/'+id+"/cancel",
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(){
            alert('예약이 취소되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};
main.init();