<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="modal fade" id="newCate" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">New</div>
							<div class="card-body">
								<div class="card-title">
									<h3 class="text-center title-2">Category</h3>
								</div>
								<hr>
								<form method="POST" enctype="multipart/form-data"
									id="fileUploadForm">
									<div class="form-group">
										<label for="cc-payment" class="control-label mb-1">Title</label>
										<input id="cateName" name="name" type="text"
											class="form-control" aria-required="true" data-val="true"
											data-val-required="Please enter the name"
											aria-invalid="false" placeholder="name">
									</div>
									<div class="form-group has-success">
										<label for="cc-name" class="control-label mb-1">Description</label>
										<textarea id="summernote" name="description" type="text"></textarea>
										<span class="help-block field-validation-valid"
											data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
									</div>

									<div class="form-control has-success">
										<label for="cc-name" class="control-label mb-1"></label> <img
											id="output" class="img-rounded" alt="" width="250"
											height="200" src="" />
										<p>
											<label for="ufile" style="cursor: pointer">chon file
												anh</label>
										</p>
										<input type="hidden" id="avatarName" name="avatarName"
											value="" /> <input name="file" id="ufile" type="file"
											style="display: none" onchange="loadFile(event)" />
									</div>
									<div>
										<button onclick="addNewCate(event);" type="submit"
											class="btn btn-lg btn-info btn-block">
											<span id="payment-button-amount">Submit</span> <span
												id="payment-button-sending" style="display: none;">Sending</span>
										</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<div class="modal fade" id="detailsCategory" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body"></div>
			<div class="modal-footer"></div>
		</div>

	</div>
</div>
