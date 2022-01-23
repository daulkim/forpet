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
            serviceType: $('#serviceType$').val(),
            startTime: $('#reserveDate$').val()+$('#startTime').val(),
            endTime: $('#endTime').val()+$('#endTime').val(),
            pet: $('#pet').val()
        };

        $.ajax({
            type: 'POST',
            url: '/reservations',
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
            startTime: $('#reserveDate$').val()+$('#startTime').val(),
            endTime: $('#endTime').val()+$('#endTime').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/reservations/'+id,
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
            url: '/reservations/'+id+"/cancel",
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