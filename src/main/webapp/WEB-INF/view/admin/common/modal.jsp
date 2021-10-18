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
										<textarea class="summernote" name="description" type="text"></textarea>
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

<div class="modal fade" id="updateCate" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">Update</div>
							<div class="card-body">
								<div class="card-title">
									<h3 class="text-center title-2">Category</h3>
								</div>
								<hr>
								<form method="POST" enctype="multipart/form-data"
									id="updateCateForm">
									<input type="hidden" id="cateId" name="id" />
									<div class="form-group">
										<label for="cc-payment" class="control-label mb-1">Title</label>
										<input id="updateCateName" name="name" type="text"
											class="form-control" aria-required="true" data-val="true"
											data-val-required="Please enter the name"
											aria-invalid="false" placeholder="name" value="">
									</div>
									<div class="form-group has-success">
										<label for="cc-name" class="control-label mb-1">Description</label>
										<textarea id="description" class="summernote" rows="4"
											cols="40" name="description"></textarea>
										<span class="help-block field-validation-valid"
											data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
									</div>

									<div class="form-control has-success">
										<label for="cc-name" class="control-label mb-1"></label> <img
											id="outputUpdateCate" class="img-rounded" alt="" width="250"
											height="200" src="" />
										<p>
											<label for="ufile1" style="cursor: pointer">chon file
												anh</label>
										</p>
										<input type="hidden" id="avatarNameUpdating" name="avatarName"
											value="" /> <input name="file" id="ufile1" type="file"
											style="display: none" onchange="loadFileUpdateCate(event)" />
									</div>
									<div>
										<input type="hidden" id="currentPageCate"
											name="currentPageCate" />
										<button onclick="updateCategory(event);" type="submit"
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
			<div class="modal-body" id="modal-body"></div>
			<div class="modal-footer"></div>
		</div>

	</div>
</div>

<div class="modal fade" id="confirmDeleteCategory" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>Are you sure</p>
			</div>
			<div class="modal-footer">
				<input type="hidden" id="idForDelete" />
				<button type="button" id="buttonDeleteCategory"
					class="btn btn-primary">Delete</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>












<div class="modal fade" id="newProduct" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body">
				<div class="row">
					<form method="POST" enctype="multipart/form-data"
						id="fileUploadForm">
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header">New</div>
								<div class="card-body">
									<div class="card-title">
										<h3 class="text-center title-2">Product</h3>
									</div>
									<hr>

									<div class="form-group">
										<label for="cc-payment" class="control-label mb-1">Title</label>
										<input id="productName" name="name" type="text"
											class="form-control" aria-required="true" data-val="true"
											data-val-required="Please enter the name"
											aria-invalid="false" placeholder="name">
									</div>
									<div class="form-group has-success">
										<label for="cc-name" class="control-label mb-1">Description</label>
										<textarea class="summernote" name="short_description" type="text"></textarea>
										<span class="help-block field-validation-valid"
											data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
									</div>
									
									<div class="form-group has-success">
										<label for="cc-name" class="control-label mb-1">Description</label>
										<textarea class="summernote" name="detail_description" type="text"></textarea>
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

								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header">New</div>
								<div class="card-body">
									<div class="card-title">
										<h3 class="text-center title-2">Product</h3>
									</div>
									<hr>

									<div class="form-group">
										<label for="cc-payment" class="control-label mb-1">Title</label>
										<input id="cateName" name="name" type="text"
											class="form-control" aria-required="true" data-val="true"
											data-val-required="Please enter the name"
											aria-invalid="false" placeholder="name">
									</div>
									<div class="form-group has-success">
										<label for="cc-name" class="control-label mb-1">Description</label>
										<textarea class="summernote" name="description" type="text"></textarea>
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

								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>


