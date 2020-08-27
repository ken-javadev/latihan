
function validate(obj) {
    var msg = '<ul class="errorList">';
    for (var i = 0; i < _vvalObjs.length; i++) {
        switch (_vvalObjs[i].val.toLowerCase()) {
            case 'required':
                if (_vvalObjs[i].obj.attr('type') == 'radio' || _vvalObjs[i].obj.attr('type') == 'checkbox') {
                    if (typeof $(':input[name="' + _vvalObjs[i].fieldname + '"]:checked').val() !== 'undefined') {
                        if ((_vvalObjs[i].msg == '') && ($(':input[name="' + _vvalObjs[i].fieldname + '"]:checked').val() == null || $(':input[name="' + _vvalObjs[i].fieldname + '"]:checked').val().replace(/\s+/, '') == ''))
                            _vvalObjs[i].msg = 'Kolom ' + _vvalObjs[i].name + ' harus diisi.';
                    } else {
                        _vvalObjs[i].msg = 'Kolom ' + _vvalObjs[i].name + ' harus diisi.';
                    }
                } else {
                    if ((_vvalObjs[i].msg == '') && (_vvalObjs[i].obj.val() == null || _vvalObjs[i].obj.val().replace(/\s+/, '') == ''))
                        _vvalObjs[i].msg = 'Kolom ' + _vvalObjs[i].name + ' harus diisi.';
                }
                break;
            case 'number':
                if ((_vvalObjs[i].msg == '') && isNaN(_vvalObjs[i].obj.val()))
                    _vvalObjs[i].msg = 'Kolom ' + _vvalObjs[i].name + ' harus angka.';
                break;
            case 'date':
                if ((_vvalObjs[i].msg == '') && isValidDate(_vvalObjs[i].obj.val()))
                    _vvalObjs[i].msg = 'Kolom ' + _vvalObjs[i].name + ' bukan tanggal yang valid. Date format: dd-mmm-yyyy.';
                break;
            case 'email':
                if ((_vvalObjs[i].msg == '') && isValidEmail(_vvalObjs[i].obj.val()))
                    _vvalObjs[i].msg = 'Kolom ' + _vvalObjs[i].name + ' bukan email yang valid.';
                break;
        }
    }
    var dmsg = '';
    for (var i = 0; i < _vvalObjs.length; i++) {
        if (_vvalObjs[i].msg !== '') {
            dmsg += '<li>' + _vvalObjs[i].msg + '</li>';
            _vvalObjs[i].obj.closest('.form-group').addClass('has-error has-feedback');
        }
    }
    if (dmsg !== '') {
        msg += dmsg + '</ul>';
        _fw_setMessage(obj, 0, msg);
        return false;
    }
    return true;
}