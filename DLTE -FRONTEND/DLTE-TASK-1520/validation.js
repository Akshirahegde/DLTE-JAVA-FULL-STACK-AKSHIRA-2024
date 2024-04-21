const performValidate=()=>{
    var isValid=true
    var myForm=document.forms['application']
    const number=myForm.accountnumber.value
    const holder=myForm.account-holder.value
    const type=myForm.account-type.value
    const chequebook=myForm.cheque-book-type.value
    const date=myForm.date-of-apply.value
    const email=myForm.email.value
    const address=myForm.address.value
    const signature=myForm.signature.value

    var numberErr=document.getElementById("numberErr")
    var holderErr=document.getElementById("holderErr")
    var typeErr=document.getElementById("typeErr")
    var chequebookErr=document.getElementById("chequebookErr")
    var dateErr=document.getElementById("dateErr")
    var emailErr=document.getElementById("emailErr")
    var addressErr=document.getElementById("addressErr")
    var signatureErr=document.getElementById("signatureErr")
//validating number
    try{
        if(!(/[0-9]{7,}/).test(number)){
            throw "Requires only positive numbers"
        }
    }catch(message){
        isValid=false
        numberErr.innerHTML=message
    }

//valdiating holder name
try{
    if(!(/[A-Za-z]/).test(holder)){
        throw "Requires only alphabets"
    }
}
catch(message){
    isValid=false
    nameErr.innerHTML=message
}

return isValid
}
