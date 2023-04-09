import { MdDeleteForever } from 'react-icons/md';

const Note = ({ id, content, handleDeleteNote}) => {

	function editNote(id) {
		window.location.href = `edit/${id}`;
	  }
	return (
		<div className='note'>
			<span>{content}</span>
			<div className='note-footer'>
                <button className='save' onClick={() => editNote(id)}>
					Edit
				</button>
				<MdDeleteForever
					onClick={() => handleDeleteNote(id)}
					className='delete-icon'
					size='1.3em'
				/>
			</div>
		</div>
	);
};

export default Note;