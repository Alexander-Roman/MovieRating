<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../template/metadata.jsp"/>
<body>
<div class="table-layout">

    <jsp:include page="../template/header.jsp"/>

    <main class="page-main flex-middle">
        <div class="row">
            <div class="main">
                <h2>Hitchhiker’s Guide to the Galaxy</h2>
                <div class="flex flex-wrap">
                    <img class="poster" src="<c:url value="/static/img/Poster.jpg"/>" alt="">
                    <div class="movie-description">
                        <p><b>Director:</b> Garth Jennings</p>
                        <p><b>Release year:</b> 2005</p>
                        <p><b>Rating:</b> 9.1 / 10 (3)</p>
                        <p>Mere seconds before the Earth is to be demolished by an alien construction crew, journeyman
                            Arthur Dent is swept off the planet by his friend Ford Prefect, a researcher penning a new
                            edition of "The Hitchhiker's Guide to the Galaxy."</p>
                    </div>
                </div>

                <h3>Your rating:</h3>
                <form class="custom-radio" action="${pageContext.request.contextPath}/action" method="post">
                <label class="radio-container">1
                    <input type="radio" name="rating"  value="1" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">2
                    <input type="radio" name="rating"  value="2" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">3
                    <input type="radio" name="rating"  value="3" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">4
                    <input type="radio" name="rating"  value="4" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">5
                    <input type="radio" name="rating"  value="5" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">6
                    <input type="radio" name="rating"  value="6" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">7
                    <input type="radio" name="rating"  value="7" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">8
                    <input type="radio" name="rating"  value="8" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">9
                    <input type="radio" name="rating"  value="9" required>
                    <span class="radio-checkmark"></span>
                </label>
                <label class="radio-container">10
                    <input type="radio" name="rating"  value="10" required>
                    <span class="radio-checkmark"></span>
                </label>
                    <input type="submit" value="Submit">
                </form>

                <h3>Comments:</h3>
                <div class="comment-container">
                    <p><b>Author</b></p>
                    <p>Hello, World!</p>
                    <span class="comment-time">11:00</span>
                </div>
                <div class="comment-container">
                    <p><b>Author</b></p>
                    <p>Some comment text...</p>
                    <span class="comment-time">11:01</span>
                </div>
                <div class="comment-container">
                    <p><b>Author</b></p>
                    <p>Some comment text...</p>
                    <span class="comment-time">11:02</span>
                </div>
                <div class="comment-container">
                    <p><b>Author</b></p>
                    <p>Some comment text...</p>
                    <span class="comment-time">11:05</span>
                </div>

                <div class="comment-form-container">
                    <form action="<c:url value="/controller?command=leaveComment"/>" method="post">
                        <div class="comment-form-row">
                            <h3><label for="text">Leave yours:</label></h3>
                        </div>
                        <div class="comment-form-row">
                            <textarea id="text" name="text" placeholder="Write something.."
                                      style="height:200px"></textarea>
                        </div>
                        <div class="comment-form-row">
                            <input type="submit" value="Submit">
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </main>

    <jsp:include page="../template/footer.jsp"/>

</div>
</body>
</html>